//package com.example.demo.Config;
//
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//
//@Configuration
////@KeycloakConfiguration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
//public class SecurityConfig{
//
//    @Bean
//    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver(){
//        return new KeycloakSpringBootConfigResolver();
//    }
//
//    @Bean
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy(){
//        return new NullAuthenticatedSessionStrategy();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .authorizeHttpRequests((authz)->
//                        authz
//                                .anyRequest()
//                                .permitAll());
//
//        http
//                .sessionManagement(sess->sess
//                        .sessionCreationPolicy(
//                                SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }
//
//
//
//
//
//}
//
