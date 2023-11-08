package com.pellto.youtoy.domain.channel.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public class Channel {
    private final Long id;
    private final Long ownerId;
    private String handle;
    private String displayName;
    private String description;
    private String banner;
    private String profile;
    private final LocalDateTime createdAt;

    @Builder
    public Channel(
            Long id,
            Long ownerId,
            String displayName,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.ownerId = Objects.requireNonNull(ownerId);
        this.displayName = Objects.requireNonNull(displayName);
        this.handle = makeRandomHandle();
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;

        // TODO: change DB nullable
        this.description = "description";
        this.banner = "banner";
        this.profile = "profile";
    }

    public String makeRandomHandle() {
        String prefix = "user-";
        int initHandleLength = 10;
        boolean useLetters = true;
        boolean useNumbers = true;

        String handle = RandomStringUtils.random(initHandleLength, useLetters, useNumbers);
        return  prefix + handle;
    }
}
