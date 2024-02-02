package com.pellto.youtoy.global.dto.video;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record VideoDto(
    Long id,
    Long channelId,
    String encodingRequestId,
    Long likeCount,
    Long viewCount,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    VideoDetailInfoDto videoDetailInfo,
    VideoEncodedInfoDto videoEncodedInfo
) {

}
