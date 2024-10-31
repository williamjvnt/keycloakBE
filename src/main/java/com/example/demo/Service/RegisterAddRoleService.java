//package com.example.demo.Service;
//
//import lombok.Value;
//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.keycloak.admin.client.resource.ClientResource;
//import org.keycloak.admin.client.resource.UserResource;
//import org.keycloak.admin.client.resource.UsersResource;
//import org.keycloak.representations.account.ClientRepresentation;
//import org.keycloak.representations.idm.RoleRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//public class RegisterAddRoleService {
//
//    private Final Keycloak keycloak;
//    @Value("${keycloak.auth-server-url}")
//    private String serverUrl;
//
//    @Value("${keycloak.realm}")
//    private String realm;
//
//    @Value("${keycloak.resource}")
//    private String clientId;
//
//    @Value("${admin-user}")
//    private String adminUsername;
//
//    @Value("${admin-password}")
//    private String adminPassword;
//
//    @Value("${keycloak.credentials.secret}")
//    private String secret;
//
//    private final Map<String, List<String>> roleMapping = new HashMap<>();
//
//    public UserRepresentation createKeycloakUser(AppUser appUser) {
//        if (appUser instanceof Student) {
//            this.roleMapping.put(this.clientId, appUser.getKeycloakRoleAsList());
//        } else if (appUser instanceof Teacher) {
//            this.roleMapping.put(this.clientId, appUser.getKeycloakRoleAsList());
//        } else {
//            this.roleMapping.put(this.clientId, Collections.singletonList(""));
//        }
//        Keycloak adminKeycloak = getAdminKeycloak();
//        CredentialRepresentation cr = new CredentialRepresentation();
//        cr.setType(OAuth2Constants.PASSWORD);
//        cr.setValue(appUser.getPassword());
//
//        UserRepresentation userRepresentation = new UserRepresentation();
//        userRepresentation.setUsername(appUser.getUsername());
//        userRepresentation.setClientRoles(roleMapping);
//        userRepresentation.setCredentials(Collections.singletonList(cr));
//        userRepresentation.setEnabled(true);
//
//        adminKeycloak.realm(realm).users().create(userRepresentation);
//        List<UserRepresentation> userList = adminKeycloak.realm(realm).users().search(appUser.getUsername()).stream()
//                .filter(userRep -> userRep.getUsername().equals(appUser.getUsername())).collect(Collectors.toList());
//        userRepresentation = userList.get(0);
//        logger.info("User with id: " + userRepresentation.getId() + " created");
//
//
//        this.assignRoleToUser(userRepresentation.getId(), appUser.getKeycloakRoleAsList().get(0));
//
//        return userRepresentation;
//    }
//
//    private void assignRoleToUser(String userId, String role) {
//        Keycloak keycloak = getAdminKeycloak();
//        UsersResource usersResource = keycloak.realm(realm).users();
//        UserResource userResource = usersResource.get(userId);
//
//        //getting client
//        ClientRepresentation clientRepresentation = keycloak.realm(realm).clients().findAll().stream().filter(client -> client.getClientId().equals(clientId)).collect(Collectors.toList()).get(0);
//        ClientResource clientResource = keycloak.realm(realm).clients().get(clientRepresentation.getId());
//        //getting role
//        RoleRepresentation roleRepresentation = clientResource.roles().list().stream().filter(element -> element.getName().equals(role)).collect(Collectors.toList()).get(0);
//        //assigning to user
//        userResource.roles().clientLevel(clientRepresentation.getId()).add(Collections.singletonList(roleRepresentation));
//    }
//
//    private Keycloak getAdminKeycloak() {
//        return KeycloakBuilder.builder().serverUrl(serverUrl)
//                .realm(realm)
//                .clientId(clientId)
//                .username(adminUsername)
//                .password(adminPassword)
//                .grantType("password")
//                .clientSecret(secret)
//                .resteasyClient(
//                        new ResteasyClientBuilder()
//                                .connectionPoolSize(10).build()
//                ).build();
//    }
//
//    public void deleteKeycloakUser(Student student) {
//        Keycloak keycloak = getAdminKeycloak();
//        List<UserRepresentation> userList = keycloak.realm(realm).users().search(student.getUsername());
//        for (UserRepresentation user : userList) {
//            if (user.getUsername().equals(student.getUsername())) {
//                keycloak.realm(realm).users().delete(user.getId());
//            }
//        }
//}
