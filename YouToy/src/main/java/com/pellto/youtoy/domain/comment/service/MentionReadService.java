package com.pellto.youtoy.domain.comment.service;

import com.pellto.youtoy.domain.comment.dto.MentionDto;
import com.pellto.youtoy.domain.comment.entity.Mention;
import com.pellto.youtoy.domain.comment.repository.MentionRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class MentionReadService {
    private final MentionRepository mentionRepository;
    private static final Pattern MENTION_PATTERN = Pattern.compile("@(.*?) ");

    public MentionDto getById(Long id) {
        var mention = mentionRepository.findById(id).orElse(null);
        Assert.notNull(mention, ErrorCode.NOT_EXIST_MENTION.getMessage());
        return toDto(mention);
    }

    public MentionDto toDto(Mention mention) {
        return new MentionDto(
                mention.getId(),
                mention.getCommentId(),
                mention.getMentionedChannelId(),
                mention.getCreatedAt()
        );
    }

    public ArrayList<String> extractChannelHandle(String content) {
        var matcher = MENTION_PATTERN.matcher(content);
        ArrayList<String> results = new ArrayList<>();
        while (matcher.find()) {
            results.add(matcher.group().strip());
        }
        return results;
    }

    public boolean hasMention(String content) {
        return MENTION_PATTERN.matcher(content).find();
    }
}
