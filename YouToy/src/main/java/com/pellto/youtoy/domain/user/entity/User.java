package com.pellto.youtoy.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public class User {
    private final Long id;
    private final String email;
    private final String pwd;
    private final LocalDateTime createdAt;

    @Builder
    public User(Long id, String email, String pwd, LocalDateTime createdAt) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.pwd = Objects.requireNonNull(pwd);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
