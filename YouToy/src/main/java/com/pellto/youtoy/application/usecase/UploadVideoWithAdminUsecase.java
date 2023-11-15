package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelAdminReadService;
import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.video.dto.UploadVideoCommand;
import com.pellto.youtoy.domain.video.entity.Video;
import com.pellto.youtoy.domain.video.service.VideoWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UploadVideoWithAdminUsecase {
    private final UserReadService userReadService;
    private final ChannelReadService channelReadService;
    private final ChannelAdminReadService channelAdminReadService;
    private final VideoWriteService videoWriteService;

    // TODO: entity to dto in usecase
    public Video execute(UploadVideoCommand cmd) {
        if (!channelReadService.isExist(cmd.channelId())) {
            throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_CHANNEL.getMessage());
        }
        if (!userReadService.isExist(cmd.userId())) {
            throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_USER.getMessage());
        }
        if (!channelReadService.isOwner(cmd.channelId(), cmd.userId()) && !channelAdminReadService.isAdmin(cmd.channelId(), cmd.userId())) {
            throw new UnsupportedOperationException(ErrorCode.NOT_AUTHORIZED_USER.getMessage());
        }
        return videoWriteService.upload(cmd);
    }
}
