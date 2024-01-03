package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.base.repository.ReplyRepository;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.domain.VideoReplyComment;

public interface VideoReplyCommentRepository extends
    ReplyRepository<VideoReplyComment, Long, VideoComment> {

}
