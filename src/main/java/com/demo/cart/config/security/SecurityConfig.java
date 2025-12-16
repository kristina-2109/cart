package com.demo.cart.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;


@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain resourceServerSecurityFilterChain(
      HttpSecurity http,
      Converter<Jwt, AbstractAuthenticationToken> authenticationConverter) throws Exception {
    http.oauth2ResourceServer(resourceServer -> {
      resourceServer.jwt(jwtDecoder -> {
        jwtDecoder.jwtAuthenticationConverter(authenticationConverter);
      });
    });

    http.sessionManagement(sessions -> {
      sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }).csrf(csrf -> {
      csrf.disable();
    });

    http.authorizeHttpRequests(requests -> {
      requests.requestMatchers("/actuator/**", "/swagger-ui/**").permitAll();
      requests.requestMatchers("/api/v1/**")
          .permitAll()
          .anyRequest().authenticated();
    });

    return http.build();
  }

  @Bean
  JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(new JwtAuthoritiesMapper());
    return converter;
  }

}

