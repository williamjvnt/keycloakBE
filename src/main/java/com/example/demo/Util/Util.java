package com.example.demo.Util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Component;

import java.util.Base64;


@Component
@AllArgsConstructor
@Log4j2
public class Util {

    private final ObjectMapper objectMapper;

    public AccessToken readAccessToken(String token){

        AccessToken tokenMapping = null;

        try{
            tokenMapping = objectMapper.readValue(Base64.getDecoder().decode(token.split(" ")[1].split("\\.")[1]), AccessToken.class);
        }catch(Exception e){
            System.err.println("anu");
        }

        return tokenMapping;
    }
}
