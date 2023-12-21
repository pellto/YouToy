package com.pellto.youtoy.domain.channel.repository;

import com.pellto.youtoy.domain.channel.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
