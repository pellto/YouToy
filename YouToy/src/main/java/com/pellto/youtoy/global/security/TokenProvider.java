package com.pellto.youtoy.global.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@PropertySource("classpath:jwt.yml")
@Service
public class TokenProvider {

  private final String secretKey;
  private final long expirationMinutes;
  private final String issuer;

  public TokenProvider(
      @Value("${secret-key}") String secretKey,
      @Value("${expiration-minutes}") long expirationMinutes,
      @Value("${issuer}") String issuer
  ) {
    this.secretKey = secretKey;
    this.expirationMinutes = expirationMinutes;
    this.issuer = issuer;
  }

  public String createToken(String userSpecification) {
    return Jwts.builder()
        .signWith(new SecretKeySpec(secretKey.getBytes(),
            SignatureAlgorithm.HS512.getJcaName()))
        .setSubject(userSpecification)
        .setIssuer(issuer)
        .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
        .setExpiration(
            Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
        .compact();
  }

  public String validateTokenAndGetSubject(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secretKey.getBytes())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}

