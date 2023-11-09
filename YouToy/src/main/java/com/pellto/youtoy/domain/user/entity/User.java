package com.pellto.youtoy.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
public class User {
    private final Long id;
    private String email;
    private String pwd;
    private String name;
    private LocalDate birthDate;
    private final LocalDateTime createdAt;

    @Builder
    public User(
            Long id,
            String email,
            String pwd,
            String name,
            LocalDate birthDate,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.pwd = Objects.requireNonNull(pwd);
        this.name = name == null ? "user" : name;
        this.birthDate = Objects.requireNonNull(birthDate);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
