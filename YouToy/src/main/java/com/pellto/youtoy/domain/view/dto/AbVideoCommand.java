package com.pellto.youtoy.domain.view.dto;

import lombok.ToString;

@ToString
public class AbVideoCommand {

  Long channelId;
  Long userId;
  String title;
  String description;

  public AbVideoCommand(Long channelId, Long userId, String title, String description) {
    this.channelId = channelId;
    this.userId = userId;
    this.title = title;
    this.description = description;
  }

  public Long channelId() {
    return channelId;
  }

  public String description() {
    return description;
  }

  public String title() {
    return title;
  }

  public Long userId() {
    return userId;
  }
}
