package com.example.demo.Service;

import com.example.demo.Config.KeycloakProperties;
import com.example.demo.Util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class GetToken {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KeycloakProperties keycloakProperties;

    @Autowired
    Util util;

    public String getToken() throws JsonProcessingException {
        String url = keycloakProperties.getLoginRestApiRealm();
        String id = keycloakProperties.getClientId();
        String secret = keycloakProperties.getClientSecret();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String,String> body = new LinkedMultiValueMap<>();

        body.add("grant_type","client_credentials");
        body.add("client_id",id);
        body.add("client_secret",secret);

        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        String response = restTemplate.postForEntity(url, request, String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);

        return jsonNode.get("access_token").asText();

    }

    public String getSub (String auth){
        AccessToken token = util.readAccessToken(auth);
        return token.getSubject();
    }




}
