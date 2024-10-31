package com.example.demo.Config;


import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.adapters.action.GlobalRequestResult;
import org.keycloak.representations.idm.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public class KeycloakConfig {

    private final KeycloakProperties keycloakProperties;

    public KeycloakConfig(KeycloakProperties keycloakProperties) {
        this.keycloakProperties = keycloakProperties;
    }

    @Bean
    public RealmResource realmResource () {
        return new RealmResource() {
            @Override
            public RealmRepresentation toRepresentation() {
                return null;
            }

            @Override
            public void update(RealmRepresentation realmRepresentation) {

            }

            @Override
            public ClientsResource clients() {
                return null;
            }

            @Override
            public ClientScopesResource clientScopes() {
                return null;
            }

            @Override
            public List<ClientScopeRepresentation> getDefaultDefaultClientScopes() {
                return List.of();
            }

            @Override
            public void addDefaultDefaultClientScope(String s) {

            }

            @Override
            public void removeDefaultDefaultClientScope(String s) {

            }

            @Override
            public List<ClientScopeRepresentation> getDefaultOptionalClientScopes() {
                return List.of();
            }

            @Override
            public void addDefaultOptionalClientScope(String s) {

            }

            @Override
            public void removeDefaultOptionalClientScope(String s) {

            }

            @Override
            public ClientRepresentation convertClientDescription(String s) {
                return null;
            }

            @Override
            public UsersResource users() {
                return null;
            }

            @Override
            public RolesResource roles() {
                return null;
            }

            @Override
            public RoleByIdResource rolesById() {
                return null;
            }

            @Override
            public GroupsResource groups() {
                return null;
            }

            @Override
            public void clearEvents() {

            }

            @Override
            public List<EventRepresentation> getEvents() {
                return List.of();
            }

            @Override
            public List<EventRepresentation> getEvents(List<String> list, String s, String s1, String s2, String s3, String s4, Integer integer, Integer integer1) {
                return List.of();
            }

            @Override
            public void clearAdminEvents() {

            }

            @Override
            public List<AdminEventRepresentation> getAdminEvents() {
                return List.of();
            }

            @Override
            public List<AdminEventRepresentation> getAdminEvents(List<String> list, String s, String s1, String s2, String s3, String s4, String s5, String s6, Integer integer, Integer integer1) {
                return List.of();
            }

            @Override
            public List<AdminEventRepresentation> getAdminEvents(List<String> list, String s, String s1, String s2, String s3, String s4, List<String> list1, String s5, String s6, Integer integer, Integer integer1) {
                return List.of();
            }

            @Override
            public RealmEventsConfigRepresentation getRealmEventsConfig() {
                return null;
            }

            @Override
            public void updateRealmEventsConfig(RealmEventsConfigRepresentation realmEventsConfigRepresentation) {

            }

            @Override
            public GroupRepresentation getGroupByPath(String s) {
                return null;
            }

            @Override
            public List<GroupRepresentation> getDefaultGroups() {
                return List.of();
            }

            @Override
            public void addDefaultGroup(String s) {

            }

            @Override
            public void removeDefaultGroup(String s) {

            }

            @Override
            public IdentityProvidersResource identityProviders() {
                return null;
            }

            @Override
            public void remove() {

            }

            @Override
            public List<Map<String, String>> getClientSessionStats() {
                return List.of();
            }

            @Override
            public ClientInitialAccessResource clientInitialAccess() {
                return null;
            }

            @Override
            public ClientRegistrationPolicyResource clientRegistrationPolicy() {
                return null;
            }

            @Override
            public Response partialImport(PartialImportRepresentation partialImportRepresentation) {
                return null;
            }

            @Override
            public RealmRepresentation partialExport(Boolean aBoolean, Boolean aBoolean1) {
                return null;
            }

            @Override
            public AuthenticationManagementResource flows() {
                return null;
            }

            @Override
            public AttackDetectionResource attackDetection() {
                return null;
            }

            @Override
            public Response testLDAPConnection(String s, String s1, String s2, String s3, String s4, String s5) {
                return null;
            }

            @Override
            public Response testLDAPConnection(TestLdapConnectionRepresentation testLdapConnectionRepresentation) {
                return null;
            }

            @Override
            public List<LDAPCapabilityRepresentation> ldapServerCapabilities(TestLdapConnectionRepresentation testLdapConnectionRepresentation) {
                return List.of();
            }

            @Override
            public Response testSMTPConnection(String s) {
                return null;
            }

            @Override
            public Response testSMTPConnection(Map<String, String> map) {
                return null;
            }

            @Override
            public void clearRealmCache() {

            }

            @Override
            public void clearUserCache() {

            }

            @Override
            public void clearKeysCache() {

            }

            @Override
            public GlobalRequestResult pushRevocation() {
                return null;
            }

            @Override
            public GlobalRequestResult logoutAll() {
                return null;
            }

            @Override
            public void deleteSession(String s, boolean b) {

            }

            @Override
            public ComponentsResource components() {
                return null;
            }

            @Override
            public UserStorageProviderResource userStorage() {
                return null;
            }

            @Override
            public KeyResource keys() {
                return null;
            }

            @Override
            public RealmLocalizationResource localization() {
                return null;
            }

            @Override
            public ClientPoliciesPoliciesResource clientPoliciesPoliciesResource() {
                return null;
            }

            @Override
            public ClientPoliciesProfilesResource clientPoliciesProfilesResource() {
                return null;
            }

            @Override
            public OrganizationsResource organizations() {
                return null;
            }

            @Override
            public ClientTypesResource clientTypes() {
                return null;
            }
        };
    }

    @Bean
    Keycloak keycloak() {
        ResteasyClientBuilder builder = (ResteasyClientBuilder) ClientBuilder.newBuilder();
        builder.connectionPoolSize(10);
        builder.maxPooledPerRoute(50);
        builder.readTimeout(1000,TimeUnit.MILLISECONDS);
        builder.connectTimeout(1000, TimeUnit.MILLISECONDS);
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/auth")
                .realm("restapi")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakProperties.id)
                .clientSecret(keycloakProperties.clientSecret)
                .resteasyClient(builder.build())
                .build();
    }
}
