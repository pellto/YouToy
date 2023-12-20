package com.pellto.youtoy.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class Mention {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  protected Long id;
  // ForeignKey
  @Column(name = "fk_mentioned_channelId")
  protected String mentionedChannelId;
  @Column
  protected LocalDateTime createdAt;
}
