package com.pellto.youtoy.subscribe.application.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeJpaDataRepository extends JpaRepository<SubscribeEntity, Long> {

  Optional<SubscribeEntity> findBySubscriberIdAndSubscribedChannelId(
      Long subscriberId,
      Long subscribedChannelId
  );

}
