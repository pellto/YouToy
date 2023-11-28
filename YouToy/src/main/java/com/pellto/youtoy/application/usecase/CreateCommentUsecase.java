package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.video.service.VideoReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCommentUsecase {
    private final VideoReadService videoReadService;
    private final CommentReadService commentReadService;
    private final CommentWriteService commentWriteService;

    public
}
