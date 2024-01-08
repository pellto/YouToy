package com.pellto.youtoy.domain.user.domain;

import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
@Getter
@NoArgsConstructor
public class UserInfo {

  @Column(name = "email")
  @Email(message = "올바른 email 형식이 아닙니다.")
  private String email;
  @Column(name = "pwd")
  @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
  @Size(max = 32, message = "비밀번호는 32자 이하이어야 합니다.")
  private String pwd;
  @Column(name = "name")
  private String name;
  @Column(name = "birthDate")
  private LocalDateTime birthDate;

  public UserInfo(
      String email,
      String pwd,
      String name,
      LocalDateTime birthDate
  ) {
    this.email = Objects.requireNonNull(email);
    this.pwd = Objects.requireNonNull(pwd);
    this.name = Objects.requireNonNull(name);
    this.birthDate = Temporal.createdAt(birthDate);
  }

  public void encodePwd(PasswordEncoder encoder) {
    this.pwd = encoder.encode(this.pwd);
  }
}
