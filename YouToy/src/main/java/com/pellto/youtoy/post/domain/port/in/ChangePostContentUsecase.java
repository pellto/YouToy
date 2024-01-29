package com.pellto.youtoy.post.domain.port.in;

import com.pellto.youtoy.global.dto.post.PostDto;
import com.pellto.youtoy.post.domain.model.PostContent;

public interface ChangePostContentUsecase {

  PostDto changePostTitle(Long id, String title);

  PostDto changePostContent(Long id, String content);

  PostDto changePost(Long id, PostContent postContent);
}
