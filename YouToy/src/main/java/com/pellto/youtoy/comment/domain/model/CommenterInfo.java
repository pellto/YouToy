package com.pellto.youtoy.comment.domain.model;

import lombok.Builder;

@Builder
public record CommenterInfo(
    Long commenterId,
    String commenterHandle,
    String displayName,
    String profilePath
) {

}
