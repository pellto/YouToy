package com.pellto.youtoy.global.dto.post.request;

import com.pellto.youtoy.post.domain.model.PostContent;

public record ChangePostRequest(
    Long id,
    PostContent postContent
) {

}
