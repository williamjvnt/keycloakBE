package com.example.demo.Service;


import com.example.demo.Config.KeycloakProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.client.ClientBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Slf4j
@AllArgsConstructor
@Service
public class RegisterService {

    private final Keycloak keycloak;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KeycloakProperties keycloakProperties;

    @Autowired
    private GetToken getToken;

    @Autowired
    private RealmResource realmResource;

    public ResponseEntity<String> createUserKCRestApi (Map<String,String> credentials, HttpServletRequest auth)  {
        String url = keycloakProperties.getRegisterRestApiRealm();
        String token = auth.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssms");
        LocalDateTime now = LocalDateTime.now();
        String rek = dtf.format(now);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization",token);
        UserRepresentation user = new UserRepresentation();

        user.setUsername(credentials.get("username"));
        user.setEmail(credentials.get("email"));
        user.setEmailVerified(false);
        user.setEnabled(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType("password");
        credential.setValue(credentials.get("password"));
        credential.setTemporary(false);
        user.setCredentials(Collections.singletonList(credential));

        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("noRekening",Collections.singletonList(rek));
        attributes.put("jumlahSaldo",Collections.singletonList("2000000"));
        user.setAttributes(attributes);

        HttpEntity<UserRepresentation> cihuy = new HttpEntity<>(user, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, cihuy, String.class);
            if(response.getStatusCode() == HttpStatus.CREATED){
                String id = getUserID(user.getUsername(),token);
                setRole(id,keycloakProperties.getId(),token);
            }
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {

            System.err.println("Error creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    private String getUserID(String username, String token) throws JsonProcessingException {
        String url = keycloakProperties.getUserIdUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET, entity,String.class, username);
        String body = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(body);
        return jsonNode.get(0).path("id").asText();
    }

    public void setRole (String userID, String clientID, String token){
        String url = keycloakProperties.getRoleMapping();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);

        RoleRepresentation role = new RoleRepresentation();
        role.setId(keycloakProperties.getUserRoleID());
        role.setName("user");
        role.setDescription("");
        role.setComposite(false);
        role.setClientRole(true);
        role.setContainerId(keycloakProperties.getId());

        List<RoleRepresentation> roles = Collections.singletonList(role);

        HttpEntity<List<RoleRepresentation>> entity = new HttpEntity<>(roles, headers);
        restTemplate.postForEntity(url, entity, String.class, userID, clientID);
    }



    public Keycloak getAdminKeycloak() {
        ResteasyClientBuilder builder = (ResteasyClientBuilder) ClientBuilder.newBuilder();
        builder.connectTimeout(1000, TimeUnit.SECONDS).readTimeout(1000, TimeUnit.SECONDS);

        return KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getAuth())
                .realm("restapi")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakProperties.getClientId())
                .clientSecret(keycloakProperties.getClientSecret())
                .resteasyClient(builder.build()
                ).build();
    }






}
