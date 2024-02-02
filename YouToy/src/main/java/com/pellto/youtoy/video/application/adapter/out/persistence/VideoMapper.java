package com.pellto.youtoy.video.application.adapter.out.persistence;

import com.pellto.youtoy.video.domain.model.Video;
import com.pellto.youtoy.video.domain.model.VideoDetailInfo;
import com.pellto.youtoy.video.domain.model.VideoEncodedInfo;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

  public Video toDomain(VideoEntity entity) {
    var videoDetailInfo = VideoDetailInfo.builder()
        .title(entity.getTitle())
        .description(entity.getDescription())
        .hashTags(entity.getHashTags())
        .build();
    var videoEncodedInfo = VideoEncodedInfo.builder()
        .runningTimeMs(entity.getRunningTimeMs())
        .encodedState(entity.getEncodedState())
        .fileName(entity.getFileName())
        .filePath(entity.getFilePath())
        .thumbnailPath(entity.getThumbnailPath())
        .thumbnailFileName(entity.getThumbnailFileName())
        .createdAt(entity.getEncodingInfoCreatedAt())
        .updatedAt(entity.getEncodingInfoUpdatedAt())
        .build();

    return Video.builder()
        .id(entity.getId())
        .channelId(entity.getChannelId())
        .createdAt(entity.getCreatedAt())
        .likeCount(entity.getLikeCount())
        .viewCount(entity.getViewCount())
        .updatedAt(entity.getUpdatedAt())
        .encodingRequestId(entity.getEncodingRequestId())
        .videoDetailInfo(videoDetailInfo)
        .videoEncodedInfo(videoEncodedInfo)
        .build();
  }

  public VideoEntity toEntity(Video video) {
    return VideoEntity.builder()
        .id(video.getId())
        .channelId(video.getChannelId())
        .encodingRequestId(video.getEncodingRequestId())
        .title(video.getVideoDetailInfo().getTitle())
        .description(video.getVideoDetailInfo().getDescription())
        .hashTags(video.getVideoDetailInfo().getHashTags())
        .likeCount(video.getLikeCount())
        .viewCount(video.getViewCount())
        .createdAt(video.getCreatedAt())
        .updatedAt(video.getUpdatedAt())
        .runningTimeMs(video.getVideoEncodedInfo().getRunningTimeMs())
        .encodedState(video.getVideoEncodedInfo().getEncodedState())
        .fileName(video.getVideoEncodedInfo().getFileName())
        .filePath(video.getVideoEncodedInfo().getFilePath())
        .thumbnailPath(video.getVideoEncodedInfo().getThumbnailPath())
        .thumbnailFileName(video.getVideoEncodedInfo().getThumbnailFileName())
        .encodingInfoCreatedAt(video.getVideoEncodedInfo().getCreatedAt())
        .encodingInfoUpdatedAt(video.getVideoEncodedInfo().getUpdatedAt())
        .build();
  }
}
