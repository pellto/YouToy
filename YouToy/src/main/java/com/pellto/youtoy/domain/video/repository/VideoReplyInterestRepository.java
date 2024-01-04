package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.base.repository.ReplyInterestRepository;
import com.pellto.youtoy.domain.video.domain.VideoReplyComment;
import com.pellto.youtoy.domain.video.domain.VideoReplyCommentInterest;

public interface VideoReplyInterestRepository extends
    ReplyInterestRepository<VideoReplyCommentInterest, Long, VideoReplyComment> {

}
