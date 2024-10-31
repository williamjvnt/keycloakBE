package com.example.demo.Service;

import com.example.demo.Config.KeycloakProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetJumlahSaldo {
    @Autowired
    private KeycloakProperties keycloakProperties;

    @Autowired
    private RestTemplate restTemplate;

    public String getSaldo(String id, String token) throws JsonProcessingException {
        String url = keycloakProperties.getUserAttrUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity,String.class, id);

        String body = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(body);
//        JsonNode attributesNode = jsonNode.get("attributes");
        return jsonNode.get("attributes").get("jumlahSaldo").get(0).asText();

    }
}
