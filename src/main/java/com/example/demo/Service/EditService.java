package com.example.demo.Service;

import com.example.demo.Config.KeycloakProperties;
import com.example.demo.Util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.client.methods.HttpHead;
import org.keycloak.authorization.client.util.Http;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EditService {
    @Autowired
    private KeycloakProperties keycloakProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Util util;

    @Autowired
    GetJumlahSaldo getJumlahSaldo;

    public ResponseEntity<String> editSaldo(HttpServletRequest auth, Map<String,String> credentials) throws JsonProcessingException {
        String url = keycloakProperties.getEditUserUrl();
        HttpHeaders headers = new HttpHeaders();
        String token = auth.getHeader("Authorization");
        headers.add("Authorization",token);

        AccessToken accessToken = util.readAccessToken(token);

        String id = accessToken.getSubject();
        String jumlah = credentials.get("jumlah");
        String jenis = credentials.get("jenis transaksi");
        String saldo = getJumlahSaldo.getSaldo(id, token);

        int a = Integer.parseInt(jumlah);
        int b = Integer.parseInt(saldo);
        int result = b-a;
        String saldoTerkini = String.valueOf(result);
        UserRepresentation user = new UserRepresentation();

        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("jumlahSaldo", Collections.singletonList(saldoTerkini));
        user.setAttributes(attributes);

        HttpEntity<UserRepresentation> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(url,HttpMethod.PUT,entity, String.class,id);

    }

    public ResponseEntity<String> editPassword(String auth, Map<String,String> password) throws JsonProcessingException {
        String url = keycloakProperties.getEditUserUrl();
        String username= password.get("username");
        String newPass = password.get("password");
        String confirmPass = password.get("confirmPass");
        String sub = getUserID(username,auth);

        if(newPass.equals(confirmPass)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization",auth);
            UserRepresentation user = new UserRepresentation();

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType("password");
            credential.setValue(newPass);
            credential.setTemporary(false);
            user.setCredentials(Collections.singletonList(credential));

            HttpEntity<UserRepresentation> entity = new HttpEntity<>(user, headers);
            ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.PUT, entity, String.class,sub);

            return ResponseEntity.ok(response.getBody());
        }else{
            return ResponseEntity.badRequest().build();
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


}
