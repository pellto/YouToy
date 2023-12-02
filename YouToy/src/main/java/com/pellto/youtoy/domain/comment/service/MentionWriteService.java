package com.pellto.youtoy.domain.comment.service;

import com.pellto.youtoy.domain.comment.dto.CreateMentionCommand;
import com.pellto.youtoy.domain.comment.entity.Mention;
import com.pellto.youtoy.domain.comment.repository.MentionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MentionWriteService {
    private final MentionRepository mentionRepository;

    public Mention create(CreateMentionCommand cmd) {
        var mention = Mention.builder()
                .commentId(cmd.commentId())
                .mentionedChannelId(cmd.mentionedChannelId())
                .createdAt(cmd.createdAt())
                .build();
        return mentionRepository.save(mention);
    }
}
