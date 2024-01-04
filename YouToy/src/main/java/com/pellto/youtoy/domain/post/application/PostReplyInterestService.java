package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyInterestRequest;
import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.post.domain.PostReplyInterest;
import com.pellto.youtoy.domain.post.dto.PostReplyInterestDto;
import com.pellto.youtoy.domain.post.repository.PostReplyInterestRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.exception.NotExistReplyInterestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReplyInterestService implements
    WriteUpdateDeleteService<PostReplyInterestDto, WriteInterestRequest, ModifyInterestRequest>,
    ReadService<PostReplyInterest, PostReplyInterestDto> {

  private final PostReplyInterestRepository replyInterestRepository;
  private final PostReplyReadService replyReadService;

  public List<PostReplyInterestDto> findAllByInterestedReplyId(Long replyId) {
    var reply = replyReadService.getById(replyId);
    return replyInterestRepository.findAllByInterestedReply(reply).stream()
        .map(this::toDto).toList();
  }

  @Override
  public List<PostReplyInterestDto> findAll() {
    return replyInterestRepository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public PostReplyInterestDto findById(Long id) {
    var interest = replyInterestRepository.findById(id)
        .orElseThrow(NotExistReplyInterestException::new);
    return toDto(interest);
  }

  @Override
  public PostReplyInterest getById(Long id) {
    return replyInterestRepository.getReferenceById(id);
  }

  public PostReplyInterestDto toDto(
      PostReplyInterest replyInterest) {
    return new PostReplyInterestDto(
        replyInterest.getId(),
        replyInterest.getInterestedReply().getId(),
        replyInterest.getInterestingUserUuid().getValue(),
        replyInterest.isDislike()
    );
  }

  @Override
  public PostReplyInterestDto write(WriteInterestRequest writeRequest) {
    var reply = replyReadService.getById(writeRequest.contentsId());
    var userUuid = new UserUUID(writeRequest.interestingUserUuid());
    var replyInterest = PostReplyInterest.builder()
        .interestedReply(reply)
        .interestingUserUuid(userUuid)
        .dislike(writeRequest.dislike())
        .build();
    return toDto(replyInterestRepository.save(replyInterest));
  }

  @Override
  public PostReplyInterestDto modify(ModifyInterestRequest modifyRequest) {
    var interest = replyInterestRepository.findById(modifyRequest.id())
        .orElseThrow(NotExistReplyInterestException::new);

    interest.changeCheck(modifyRequest.dislike());
    interest.changeDislike();

    return toDto(interest);
  }

  @Override
  public void deleteById(Long id) {
    replyInterestRepository.deleteById(id);
  }
}
