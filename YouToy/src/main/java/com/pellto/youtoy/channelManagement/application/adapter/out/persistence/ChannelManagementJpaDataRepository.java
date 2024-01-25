package com.pellto.youtoy.channelManagement.application.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelManagementJpaDataRepository extends
    JpaRepository<ChannelManagementEntity, Long> {

  Optional<ChannelManagementEntity> findByChannelIdAndMemberId(Long channelId, Long memberId);
}
