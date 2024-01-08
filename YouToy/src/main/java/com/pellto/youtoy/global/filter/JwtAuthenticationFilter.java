package com.pellto.youtoy.global.filter;

import com.pellto.youtoy.domain.user.dto.AuthorizedUser;
import com.pellto.youtoy.global.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(0)
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final TokenProvider tokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = parseBearerToken(request);
    AuthorizedUser authorizedUser = parseUserSpecification(token);
    AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(
        authorizedUser, token, authorizedUser.getMemberType());
    authenticated.setDetails(new WebAuthenticationDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticated);

    filterChain.doFilter(request, response);

  }

  private String parseBearerToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
        .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
        .map(token -> token.substring(7))
        .orElse(null);
  }

  private AuthorizedUser parseUserSpecification(String token) {
    String[] split = Optional.ofNullable(token)
        .filter(subject -> subject.length() >= 10)
        .map(tokenProvider::validateTokenAndGetSubject)
        .orElse("anonymous:anonymous:anonymous:anonymous:anonymous")
        .split(":");

    return new AuthorizedUser(List.of(split));
  }
}
