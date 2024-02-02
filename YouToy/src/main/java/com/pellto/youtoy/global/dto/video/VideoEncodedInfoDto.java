package com.pellto.youtoy.global.dto.video;

import com.pellto.youtoy.video.domain.model.EncodedState;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record VideoEncodedInfoDto(
    Long runningTimeMs,
    EncodedState encodedState,
    String fileName,
    String filePath,
    String thumbnailPath,
    String thumbnailFileName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
