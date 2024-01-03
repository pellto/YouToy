package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.base.repository.ContentsInterestRepository;
import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.domain.VideoInterest;

public interface VideoInterestRepository extends
    ContentsInterestRepository<VideoInterest, Long, Video> {

}
