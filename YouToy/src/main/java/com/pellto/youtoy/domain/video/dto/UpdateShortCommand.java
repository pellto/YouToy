package com.pellto.youtoy.domain.video.dto;

import javax.validation.constraints.NotNull;

public class UpdateShortCommand extends AbVideoCommand {
    public UpdateShortCommand(Long channelId, Long userId, String title, String description) {
        super(channelId, userId, title, description);
    }
}
