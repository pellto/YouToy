package com.pellto.youtoy.domain.video.dto;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

public class UploadShortCommand extends AbVideoCommand {
    @ConstructorProperties({"channelId", "userId", "title", "description"})
    public UploadShortCommand(
            @NotNull Long channelId,
            @NotNull Long userId,
            String title,
            String description) {
        super(channelId, userId, title, description);
    }

    @Override
    public String toString() {
        return "UploadShortCommand{" +
                "channelId=" + channelId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
