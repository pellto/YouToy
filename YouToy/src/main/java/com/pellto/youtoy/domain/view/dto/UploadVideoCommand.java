package com.pellto.youtoy.domain.view.dto;


import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

public class UploadVideoCommand extends AbVideoCommand {
    @ConstructorProperties({"channelId", "userId", "title", "description"})
    public UploadVideoCommand(
            @NotNull Long channelId,
            @NotNull Long userId,
            String title,
            String description
    ) {
        super(channelId, userId, title, description);
    }

    @Override
    public String toString() {
        return "UploadVideoCommand{" +
                "channelId=" + channelId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
