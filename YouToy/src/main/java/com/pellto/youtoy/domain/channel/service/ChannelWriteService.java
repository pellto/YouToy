package com.pellto.youtoy.domain.channel.service;

import com.pellto.youtoy.domain.channel.dto.CreateChannelCommand;
import com.pellto.youtoy.domain.channel.dto.UpdateChannelCommand;
import com.pellto.youtoy.domain.channel.entity.Channel;
import com.pellto.youtoy.domain.channel.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelWriteService {
    private final ChannelRepository channelRepository;

    public Channel create(CreateChannelCommand cmd) {
        // TODO: change custom Error with errorHandler
        if (cmd.displayName().isEmpty()) throw new UnsupportedOperationException("채널명은 필수입니다.");
        if (cmd.ownerId() == null) throw new UnsupportedOperationException("유저의 id는 필수입니다.");

        var channel = Channel
                .builder()
                .ownerId(cmd.ownerId())
                .displayName(cmd.displayName())
                .build();

        return channelRepository.save(channel);
    }

    public void update(UpdateChannelCommand cmd) {
        var channel = channelRepository.findById(cmd.id()).orElseThrow();
        boolean changeChecker = false;
        if (cmd.handle() != null) {
            if (channelRepository.existsHandle(cmd.handle())) {
                throw new UnsupportedOperationException("이미 존재하는 handle 입니다");
            }
            channel.setHandle(cmd.handle());
            changeChecker = true;
        }
        if (cmd.displayName() != null) {
            channel.setDisplayName(cmd.displayName());
            changeChecker = true;
        }
        if (cmd.description() != null) {
            channel.setDescription(cmd.description());
            changeChecker = true;
        }
        if (cmd.banner() != null) {
            channel.setBanner(cmd.banner());
            changeChecker = true;
        }
        if (cmd.profile() != null) {
            channel.setProfile(cmd.profile());
            changeChecker = true;
        }

        if (changeChecker) channelRepository.save(channel);
    }
}
