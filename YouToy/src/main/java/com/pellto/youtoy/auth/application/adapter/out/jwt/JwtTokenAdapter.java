package com.pellto.youtoy.auth.application.adapter.out.jwt;

import com.pellto.youtoy.auth.domain.port.out.TokenServicePort;
import com.pellto.youtoy.global.dto.auth.AuthDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenAdapter implements TokenServicePort {

  private static final String SECRET_KEY = "SZPqwwAV8Wzf8Dc5gqduTbdu8Kdou26P"
      + "TXVwKJD1enFSXlwvdzIr5N9QDrx2iAAl"
      + "85KpDab3pjp8FjL9wDFv8xdtRDM2axUZ"
      + "gSApoWOPBXU2gNCEzp8uauRIfr4JFjnc"
      + "NUXUMU9iB7OHQQTFgDKH74tmGckYVA34"
      + "cBkPh6euPhdBWhzCIbCjK7czdmf99kte"
      + "wqB6KEYQnwWQPLjY0xwni9VH7N1bfhqW"
      + "us0MvkxdSmbuH613FpfazywvNdDbUEmI";
  private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

  @Override
  public String generateToken(AuthDto authDto) {
    SecretKey key = new SecretKeySpec(
        SECRET_KEY.getBytes(StandardCharsets.UTF_8),
        SIG.HS256.key().build().getAlgorithm()
    );
    var now = LocalDateTime.now();
    var expiryDate = now.plus(EXPIRATION_TIME, ChronoUnit.MILLIS);

    return Jwts.builder().subject(authDto.getAuthInfoString())
        .claim("email", authDto.email())
        .issuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
        .expiration(Date.from(expiryDate.atZone(ZoneId.systemDefault()).toInstant()))
        .signWith(key)
        .compact();
  }

  @Override
  public AuthDto getAuthDtoFromToken(String token) {
    return null;
  }
}
