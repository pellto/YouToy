package com.pellto.youtoy.domain.view.dto;

import java.beans.ConstructorProperties;
import javax.validation.constraints.NotNull;

public class UpdateShortCommand extends AbVideoCommand {

  private final Long id;

  public UpdateShortCommand(@NotNull Long channelId, @NotNull Long userId, String title,
      String description) {
    super(channelId, userId, title, description);
    id = null;
  }

  @ConstructorProperties({"id", "channelId", "userId", "title", "description"})
  public UpdateShortCommand(@NotNull Long id, @NotNull Long channelId, @NotNull Long userId,
      String title, String description) {
    super(channelId, userId, title, description);
    this.id = id;
  }

  public Long id() {
    return id;
  }

  @Override
  public String toString() {
    return "UpdateShortCommand{" +
        "id=" + id +
        ", channelId=" + channelId +
        ", userId=" + userId +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
