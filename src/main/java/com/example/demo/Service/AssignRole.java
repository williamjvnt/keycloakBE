package com.example.demo.Service;


import com.example.demo.Config.KeycloakConfig;
import com.example.demo.Config.KeycloakProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.client.ClientBuilder;
import lombok.AllArgsConstructor;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Service
public class AssignRole {

    private final RestTemplate restTemplate;

    private final KeycloakProperties keycloakProperties;


    public ResponseEntity<String> makeUser(Map<String,String> credentials, HttpServletRequest auth){
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
                List<String> anune = response.getHeaders().get("Location");
                String uerel  = anune.get(0);
                String id = uerel.substring(uerel.lastIndexOf("/")+1);
                userList(id);
            }
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {

            System.err.println("Error creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    public void userList(String userId){
        Keycloak keycloak = getAdminKeycloak();

        UserResource userResource = keycloak.realm("restapi").users().get(userId);

//        ClientRepresentation client = keycloak.realm("Resapi").clients().get();

//        ClientRepresentation clientRepresentation = keycloak.realm("restapi").clients().findByClientId(keycloakProperties.getClientId()).get(0);
//        ClientResource clientResource = keycloak.realm("restapi").clients().get(clientRepresentation.getId());

//        RoleRepresentation roleRepresentation = clientResource.roles().get("user").toRepresentation();

        RoleRepresentation role = new RoleRepresentation();
        role.setId(keycloakProperties.getUserRoleID());
        role.setName("user");
        role.setDescription("");
        role.setComposite(false);
        role.setClientRole(true);
        role.setContainerId(keycloakProperties.getId());

        userResource.roles().clientLevel(keycloakProperties.getId()).add(Collections.singletonList(role));

    }

    public Keycloak getAdminKeycloak() {
        ResteasyClientBuilder builder = (ResteasyClientBuilder) ClientBuilder.newBuilder();
        builder.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS);

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
