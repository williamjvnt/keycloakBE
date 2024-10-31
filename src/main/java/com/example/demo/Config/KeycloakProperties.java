package com.example.demo.Config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class KeycloakProperties {

    //login Section
    private final String auth = "http://localhost:8080";
    private final String loginRestApiRealm = "http://localhost:8080/realms/restapi/protocol/openid-connect/token";
    private final String loginTestingKCRealm = "http://localhost:8080/realms/testingKC/protocol/openid-connect/token";

    private final String clientIdRestApiRealm = "mobile_banking";
    private final String clientSecretRestApiRealm = "evIpLXgy6de5w4dPoJEZCwefw72uvPMJ";

    private final String clientIdTestingKCRealm = "mobile-banking";
    private final String clientSecretTestingKCRealm = "hgVoFktX0L2B9jL1cWqOPDf8CzTsVxwD";

    //register Section
    private final String getToken = "http://localhost:8085/getClientToken";
    private final String registerRestApiRealm = "http://localhost:8080/admin/realms/restapi/users";
    private final String registerTestingKCRealm = "http://localhost:8080/admin/realms/testingKC/users";

    //logout section
    private final String logoutRestApiRealm = "http://localhost:8080/realms/restapi/protocol/openid-connect/logout";
    private final String logoutTestingKCRealm = "http://localhost:8080/realms/testingKC/protocol/openid-connect/logout";

    //Token client_credentials Type
    String grantType = "client_credentials";
    String clientId = "mobile_banking";
    String clientSecret = "evIpLXgy6de5w4dPoJEZCwefw72uvPMJ";
    String id = "1439cb47-93c9-40d9-8c80-1af0dbf8d407";


    //role section
    private final String userRoleID = "ebd6dce7-1727-49b8-b12a-d73931127954";
    private final String rolesRestApiRealm = "http://localhost:8080/admin/realms/restapi/clients/1439cb47-93c9-40d9-8c80-1af0dbf8d407/roles";
    private final String userRoleRestApiRealm = "http://localhost:8080/admin/realms/restapi/clients/{id}/roles/user";
    private final String roleMapping = "http://localhost:8080/admin/realms/restapi/users/{userID}/role-mappings/clients/{clientID}";
    private final String UserIdUrl = "http://localhost:8080/admin/realms/restapi/users?username={username}";
    private final String UserAttrUrl = "http://localhost:8080/admin/realms/restapi/users/{user-id}";

    //edit section
    private final String editUserUrl = "http://localhost:8080/admin/realms/restapi/users/{user-id}";

    //delete section
    private final String deleteUserUrl = "http://localhost:8080/admin/realms/restapi/users/{user-id}";
}
