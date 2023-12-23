package com.pellto.youtoy.domain.channel.domain;

import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "channel")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Channel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "channel_id")
  private final Long id;
  @Column(name = "created_at", nullable = false)
  private final LocalDateTime createdAt;

  @Column(name = "modified_at", nullable = false)
  private LocalDateTime modifiedAt;
  @Embedded
  private ChannelInfo channelInfo;

  @OneToMany(mappedBy = "subscribed", cascade = CascadeType.REMOVE)
  private final List<Subscribe> subscribers = new ArrayList<>();
  @OneToMany(mappedBy = "subscriber", cascade = CascadeType.REMOVE)
  private final List<Subscribe> subscribeds = new ArrayList<>();

  @Builder
  public Channel(Long id, ChannelInfo channelInfo, LocalDateTime createdAt,
      LocalDateTime modifiedAt) {
    this.id = id;
    this.channelInfo = Objects.requireNonNull(channelInfo);
    this.createdAt = Temporal.createdAt(createdAt);
    this.modifiedAt = General.setNullInput(modifiedAt, this.createdAt);
  }

  public void changeChannelInfo(ChannelInfo channelInfo) {
    this.channelInfo = channelInfo;
    this.modifiedAt = LocalDateTime.now();
  }

  public Long getSubscriberCount() {
    return (long) this.subscribers.size();
  }

  public List<Long> getSubscribedList() {
    return subscribeds.stream().map(Subscribe::getId).toList();
  }
}
