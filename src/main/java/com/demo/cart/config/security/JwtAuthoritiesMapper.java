package com.demo.cart.config.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

@Slf4j
public class JwtAuthoritiesMapper implements Converter<Jwt, Collection<GrantedAuthority>> {
  private static final String ROLE_PREFIX = "ROLE_";
  private static final String REALM_ACCESS = "realm_access";
  private static final String ROLES = "roles";

  private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
    Map<String, Object> realmAccess = jwt.getClaim(REALM_ACCESS);

    if (realmAccess == null) {
      return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    List<String> roles = (List<String>) realmAccess.get(ROLES);

    if (roles == null) {
      return Collections.emptyList();
    }

    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
        .collect(Collectors.toList());
  }

  @Override
  public @Nullable Collection<GrantedAuthority> convert(Jwt jwt) {
    return extractAuthorities(jwt);
  }

}
