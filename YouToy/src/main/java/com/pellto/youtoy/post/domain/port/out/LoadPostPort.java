package com.pellto.youtoy.post.domain.port.out;

import com.pellto.youtoy.post.domain.model.Post;

public interface LoadPostPort {

  Post load(Long id);

  boolean isExistById(Long id);

}
