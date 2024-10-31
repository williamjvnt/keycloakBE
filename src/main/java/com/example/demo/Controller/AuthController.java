package com.example.demo.Controller;

import com.example.demo.Config.ResponseDocumentation;
import com.example.demo.Dto.Request.TokenRequest;
import com.example.demo.Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.HeaderParam;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Tag(name = "User Management", description = "Operasi untuk mengelola user")
@AllArgsConstructor
@RestController
public class AuthController {

    private final LoginService loginService;

    private final GetToken getToken;

    private final RegisterService registerService;

    private final LogoutService logoutService;

    private final EditService editService;

    private final DeleteService deleteService;

    private final SearchUser searchUser;

    private final AssignRole assignRole;

    @Operation(summary = "testing server is online or nahh")
    @GetMapping("/testing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenRequest.class),examples = {
                            @ExampleObject (name = "cihuy", value = "{error njir}"),
                            @ExampleObject (name = "garena", value = "{error njir}")
                    })),
            @ApiResponse(responseCode = "401", description = "Unauthorize", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
//    @RolesAllowed("user")
    public ResponseEntity<?> doTestUser(){

        return ResponseEntity.ok("Controller doTestUser OK!");
    }

    @Operation(summary = "testing server is online or nahh, but for admin")
    @RolesAllowed("user")
    @GetMapping("/testingAdmin")
    public ResponseEntity<?> doTestAdmin(){
        return ResponseEntity.ok("Controller doTestAdmin OK!");
    }

    //3
    //disini merupakan hasil dari hit dari rest api
//    @RolesAllowed("admin")
    @Operation(summary = "Login")
    @ResponseDocumentation
    @PostMapping("/login")

    public ResponseEntity<String> getToken(@RequestBody Map<String,String> credentials){
        return loginService.loginRestApiRealm(credentials);
    }

//    @RolesAllowed("admin")
    @PostMapping("/loginTestingKC")
    public ResponseEntity<String> getTokenTestingKC(@RequestBody Map<String,String> credentials){
        return loginService.loginTestingKCRealm(credentials);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUserKC (@RequestBody Map<String, String> credentials, HttpServletRequest request) {
        return registerService.createUserKCRestApi(credentials,request);
    }
    @PostMapping("/makeUser")
    public ResponseEntity<String> makeUser (@RequestBody Map<String, String> credentials, HttpServletRequest request) {
        return assignRole.makeUser(credentials,request);
    }

    @PostMapping("/getClientToken")
    public String getToken () throws JsonProcessingException {
        return getToken.getToken();
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> refreshToken) {
        return logoutService.logout(refreshToken);
    }

    @PostMapping("/transaksi")
    public ResponseEntity<String> makeTransaksi (HttpServletRequest auth ,@RequestBody Map<String,String> credentials) throws JsonProcessingException {
        return editService.editSaldo(auth,credentials);
    }

    //resetPassword
    @PostMapping("/editPassword")
    public ResponseEntity<String> editPassword(@RequestHeader(value = "Authorization", required = false) String auth,
                                               @RequestBody Map<String, String> password) throws JsonProcessingException {
        return editService.editPassword(auth,password);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestHeader(value = "Authorization", required = false) String auth,
                                             @RequestBody Map<String, String> id){
        return deleteService.deleteUser(auth,id);
    }

    @GetMapping("/decode")
    public String decode (@RequestHeader(value="Authorization", required = false) String auth){
        return getToken.getSub(auth);
    }

    @GetMapping("/searchById")
    public List<UserRepresentation> searchID(){
        return searchUser.searchByUsername("cihuy",true);
    }

    @GetMapping("/cihuy")
    public Keycloak admin(){
        return registerService.getAdminKeycloak();
    }

//    @GetMapping("/listUser")
//    public ResponseEntity<String> listUser(@RequestParam String userId){
//        return assignRole.userList(userId);
//    }
//    @PostMapping("/setRole")
//    public ResponseEntity<String> setRoles (@RequestHeader(value = "Authorization",required = false) String authorization,
//                                            HttpServletRequest request){
//
//
//        return registerService.setRole();
//    }
}
