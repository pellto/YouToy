package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.dto.CreateChannelCommand;
import com.pellto.youtoy.domain.channel.service.ChannelWriteService;
import com.pellto.youtoy.domain.user.dto.RegisterUserCommand;
import com.pellto.youtoy.domain.user.entity.User;
import com.pellto.youtoy.domain.user.service.UserWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class SigninUserUsecase {
    private final UserWriteService userWriteService;
    private final ChannelWriteService channelWriteService;

    @Transactional
    public User execute(RegisterUserCommand cmd) {
        var user = userWriteService.register(cmd);
        // TODO: create userInfo -> change user's name in userInfo
        CreateChannelCommand createChannelCommand = new CreateChannelCommand(user.getId(), user.getEmail());
        channelWriteService.create(createChannelCommand);
        return user;
    }
}
