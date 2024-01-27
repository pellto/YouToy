package com.pellto.youtoy.auth.domain.port.out;

import com.pellto.youtoy.global.dto.auth.AuthDto;

public interface TokenServicePort {


  String generateToken(AuthDto authDto);

  AuthDto getAuthDtoFromToken(String token);
}
