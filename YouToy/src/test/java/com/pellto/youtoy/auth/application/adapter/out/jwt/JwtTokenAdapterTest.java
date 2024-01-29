package com.pellto.youtoy.auth.application.adapter.out.jwt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("outAdapter")
class JwtTokenAdapterTest {

  private static final String ADAPTER_NAME = "JwtTokenAdapter";

  // TODO: define test code
  @DisplayName("[" + ADAPTER_NAME + "/generateToken] generateToken 성공 테스트")
  @Test
  void generateTokenSuccessTest() {
    Assertions.assertThat(true).isTrue();
  }
}