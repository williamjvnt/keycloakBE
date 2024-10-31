package com.example.demo.Service;

import com.example.demo.Config.KeycloakProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogoutService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KeycloakProperties keycloakProperties;

    public ResponseEntity<?> logout(Map<String,String> refreshToken){
        String url = keycloakProperties.getLogoutRestApiRealm() ;
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String,String> req = new LinkedMultiValueMap<>();

        req.add("refresh_token", refreshToken.get("refresh_token"));
        req.add("client_id", keycloakProperties.getClientId());
        req.add("client_secret", keycloakProperties.getClientSecret());


        return restTemplate.postForEntity(url, req, String.class);
    }
}
