package com.pellto.youtoy.video.application.adapter.out.persistence;

import com.pellto.youtoy.video.domain.model.EncodedState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "video")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long channelId;
  private String encodingRequestId;
  private String title;
  private String description;
  private String hashTags;
  private Long likeCount;
  private Long viewCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Long runningTimeMs;
  @Enumerated(EnumType.STRING)
  private EncodedState encodedState;
  private String fileName;
  private String filePath;
  private String thumbnailPath;
  private String thumbnailFileName;
  private LocalDateTime encodingInfoCreatedAt;
  private LocalDateTime encodingInfoUpdatedAt;
}
