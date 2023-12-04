package com.pellto.youtoy.domain.view.dto;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

public class UpdateVideoCommand extends AbVideoCommand {
    private final Long id;

    public UpdateVideoCommand(@NotNull Long channelId, @NotNull Long userId, String title, String description) {
        super(channelId, userId, title, description);
        this.id = null;
    }

    @ConstructorProperties({"id", "channelId", "userId", "title", "description"})
    public UpdateVideoCommand(@NotNull Long id, @NotNull Long channelId, @NotNull Long userId, String title, String description) {
        super(channelId, userId, title, description);
        this.id = id;
    }

    public Long id() {
        return id;
    }

    @Override
    public String toString() {
        return "UpdateVideoCommand{" +
                "id=" + id +
                ", channelId=" + channelId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
