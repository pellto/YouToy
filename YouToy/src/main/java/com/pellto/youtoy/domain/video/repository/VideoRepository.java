package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
