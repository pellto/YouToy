package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.base.repository.CommentInterestRepository;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.domain.VideoCommentInterest;

public interface VideoCommentInterestRepository extends
    CommentInterestRepository<VideoCommentInterest, Long, VideoComment> {

}
