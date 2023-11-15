package com.pellto.youtoy.domain.video.dto;

import javax.validation.constraints.NotNull;

public class UploadShortCommand extends AbVideoCommand {
    public UploadShortCommand(Long channelId, Long userId, String title, String description) {
        super(channelId, userId, title, description);
    }
}
