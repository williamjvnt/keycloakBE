package com.example.demo.Service;

import com.example.demo.Config.KeycloakProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private KeycloakProperties keycloakProperties;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> loginRestApiRealm(Map<String,String> credentials){
        String url = keycloakProperties.getLoginRestApiRealm();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("username", credentials.get("username"));
        body.add("password", credentials.get("password"));
        body.add("grant_type", "password");

        body.add("client_id", keycloakProperties.getClientIdRestApiRealm());
        body.add("client_secret", keycloakProperties.getClientSecretRestApiRealm());

        HttpEntity<LinkedMultiValueMap<String,String>> request = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity(url, request, String.class);
    }

    public ResponseEntity<String> loginTestingKCRealm(Map<String,String> credentials){
        String url = keycloakProperties.getLoginTestingKCRealm();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("username", credentials.get("username"));
        body.add("password", credentials.get("password"));
        body.add("grant_type", "password");

        body.add("client_id", keycloakProperties.getClientIdTestingKCRealm());
        body.add("client_secret", keycloakProperties.getClientSecretTestingKCRealm());

        HttpEntity<LinkedMultiValueMap<String,String>> request = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity(url, request, String.class);
    }
}
