package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.base.repository.ReplyRepository;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.domain.PostReply;

public interface PostReplyRepository extends
    ReplyRepository<PostReply, Long, PostComment> {

}
