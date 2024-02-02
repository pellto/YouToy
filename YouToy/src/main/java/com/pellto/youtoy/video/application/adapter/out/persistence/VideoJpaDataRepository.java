package com.pellto.youtoy.video.application.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoJpaDataRepository extends JpaRepository<VideoEntity, Long> {

}
