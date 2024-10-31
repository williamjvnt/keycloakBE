package com.example.demo.Service;

import com.example.demo.Config.KeycloakProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DeleteService {

    @Autowired
    private KeycloakProperties keycloakProperties;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> deleteUser(String token, Map<String,String> id){
        String url = keycloakProperties.getDeleteUserUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        String sub = id.get("id");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.DELETE,entity,String.class,sub);
    }
}
