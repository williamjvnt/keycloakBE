package com.example.demo.Service;

import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SearchUser {

    private final Keycloak keycloak;


    private static final String REALM_NAME = "restapi";

    public List<UserRepresentation> searchByUsername(String username, boolean exact){
        List<UserRepresentation> users = keycloak.realm(REALM_NAME).users().searchByUsername(username,exact);
        return users;
    }

}
