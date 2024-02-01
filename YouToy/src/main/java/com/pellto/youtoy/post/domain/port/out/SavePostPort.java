package com.pellto.youtoy.post.domain.port.out;

import com.pellto.youtoy.post.domain.model.Post;

public interface SavePostPort {

  void deleteById(Long postId);

  Post save(Post post);

  void update(Post post);
}
