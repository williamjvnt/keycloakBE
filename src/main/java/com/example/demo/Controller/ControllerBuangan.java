package com.example.demo.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerBuangan {
//    String roleUrl = "http://localhost:8080/admin/realms/restapi/users/{user-id}/role-mappings/clients/{client-id}";
//    HttpHeaders roleHeaders = new HttpHeaders();
//        roleHeaders.add("Authorization",token);
//        roleHeaders.setContentType(MediaType.APPLICATION_JSON);
//    HttpEntity<String> roleEntity = new HttpEntity<>(roleHeaders);

//    @RolesAllowed("admin")
//    @PostMapping("/login")
//    public ResponseEntity<String> getToken(@RequestBody Map<String,String> credentials){
//        RestTemplate restTemplate = new RestTemplate();
////        String url = "http://localhost:8080/realms/testingHitKC/protocol/openid-connect/token";
//        String url = "http://localhost:8080/realms/restapi/protocol/openid-connect/token";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//
//        body.add("username", credentials.get("username"));
//        body.add("password", credentials.get("password"));
//        body.add("grant_type", "password");
//
//        body.add("client_id", "mobile_banking");
//        body.add("client_secret", "evIpLXgy6de5w4dPoJEZCwefw72uvPMJ");
////        body.add("client_id", "mobile-banking");
////        body.add("client_secret", "hgVoFktX0  L2B9jL1cWqOPDf8CzTsVxwD");
//
//        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//        return ResponseEntity.ok(response.getBody());
//    }

//    @PostMapping("/getClientToken")
//    public String getToken () throws JsonProcessingException {
//        String urlToken = "http://localhost:8080/realms/restapi/protocol/openid-connect/token";
//        String grantType = "client_credentials";
//        String clientId = "mobile_banking";
//        String clientSecret = "evIpLXgy6de5w4dPoJEZCwefw72uvPMJ";
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        LinkedMultiValueMap<String,String> body = new LinkedMultiValueMap<>();
//
//        body.add("grant_type",grantType);
//        body.add("client_id",clientId);
//        body.add("client_secret",clientSecret);
//
//        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
//        String response = restTemplate.postForEntity(urlToken, request, String.class).getBody();
//        //string->json->string(value)
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(response);
//        String garena = jsonNode.path("access_token").asText();
//
//        return garena;
//    }

//    @PostMapping("/register")
//    public ResponseEntity<String> createUserKC (@RequestBody Map<String, String> credentials, HttpServletRequest request){
//        String url = "http://localhost:8080/admin/realms/restapi/users";
//
////        String urlToken = "http://localhost:8080/realms/restapi/protocol/openid-connect/token";
//        String token = request.getHeader("Authorization");
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssms");
//        LocalDateTime now = LocalDateTime.now();
//        String rek = dtf.format(now);
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization",token);
//        UserRepresentation user = new UserRepresentation();
//
//        user.setUsername(credentials.get("username"));
//        user.setEmail(credentials.get("email"));
//        user.setEmailVerified(false);
//        user.setEnabled(true);
//
//        Map<String, List<String>> attributes = new HashMap<>();
//        attributes.put("role", Collections.singletonList("Nasabah"));
////        attributes.put("tanggalLahir",Collections.singletonList(credentials.get("tanggalLahir")));
////        attributes.put("noTelf",Collections.singletonList(credentials.get("noTelf")));
//        attributes.put("noRekening",Collections.singletonList(rek));
//        attributes.put("jumlahSaldo",Collections.singletonList("2000000"));
//
//        user.setAttributes(attributes);
//
//        System.out.println("Sending user data: " + user);
//
//        HttpEntity<UserRepresentation> cihuy = new HttpEntity<>(user, headers);
//        try {
//            ResponseEntity<String> response = restTemplate.postForEntity(url, cihuy, String.class);
//            // Log the response from Keycloak
//            System.out.println("Response: " + response.getBody());
//            return ResponseEntity.ok(response.getBody());
//        } catch (Exception e) {
//            // Log the exception for debugging
//            System.err.println("Error creating user: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
//        }
//    }

//    @PostMapping("/registerTesting")
//    public ResponseEntity<String> createUserTestingKC (@RequestBody Map<String, String> credentials, HttpServletRequest request){
//        String url = "http://localhost:8080/admin/realms/testingHitKC/users";
//        String token = request.getHeader("Authorization");
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization",token);
//
//        UserRepresentation user = new UserRepresentation();
//
//        user.setUsername(credentials.get("username"));
//        user.setEmail(credentials.get("email"));
//        user.setFirstName(credentials.get("firstName"));
//        user.setLastName(credentials.get("lastName"));
//        user.setEmailVerified(false);
//        user.setEnabled(true);
//
//        HttpEntity<UserRepresentation> cihuy = new HttpEntity<>(user, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(url, cihuy, String.class);
//
//        return ResponseEntity.ok(response.getBody());
//    }
}
