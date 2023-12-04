package com.pellto.youtoy.domain.channel.entity;

import com.pellto.youtoy.util.GenerateRandomString;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
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
            String handle,
            String displayName,
            LocalDateTime createdAt,
            String description,
            String banner,
            String profile
    ) {
        this.id = id;
        this.ownerId = Objects.requireNonNull(ownerId);
        this.displayName = Objects.requireNonNull(displayName);
        // TODO: change to input nullable Object and return default by template;
        this.handle = handle == null ? makeRandomHandle() : handle;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.description = description == null ? "description" : description;
        this.banner = banner == null ? "banner" : banner;
        this.profile = profile == null ? "profile" : profile;
    }

    private String makeRandomHandle() {
        String prefix = "@user-";
        int initHandleLength = 10;
        boolean useLetters = true;
        boolean useNumbers = true;

        String handle = GenerateRandomString.make(initHandleLength, useLetters, useNumbers);
        return prefix + handle;
    }
}
