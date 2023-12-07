package com.pellto.youtoy.domain.view.dto;


import java.beans.ConstructorProperties;
import javax.validation.constraints.NotNull;

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
