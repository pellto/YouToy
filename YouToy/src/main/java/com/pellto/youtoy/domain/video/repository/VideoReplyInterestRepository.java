package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.base.repository.ReplyInterestRepository;
import com.pellto.youtoy.domain.video.domain.VideoReply;
import com.pellto.youtoy.domain.video.domain.VideoReplyInterest;

public interface VideoReplyInterestRepository extends
    ReplyInterestRepository<VideoReplyInterest, Long, VideoReply> {

}
