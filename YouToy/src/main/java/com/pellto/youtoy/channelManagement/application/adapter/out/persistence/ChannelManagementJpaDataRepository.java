package com.pellto.youtoy.channelManagement.application.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelManagementJpaDataRepository extends
    JpaRepository<ChannelManagementEntity, Long> {

  List<ChannelManagementEntity> findAllByMemberId(Long memberId);

  Optional<ChannelManagementEntity> findByChannelIdAndMemberId(Long channelId, Long memberId);
}
