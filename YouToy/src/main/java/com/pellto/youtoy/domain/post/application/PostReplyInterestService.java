package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyInterestRequest;
import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.post.domain.PostReplyInterest;
import com.pellto.youtoy.domain.post.dto.PostReplyInterestDto;
import com.pellto.youtoy.domain.post.repository.PostReplyInterestRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.exception.NotExistReplyCommentInterestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReplyInterestService implements
    WriteUpdateDeleteService<PostReplyInterestDto, WriteInterestRequest, ModifyInterestRequest>,
    ReadService<PostReplyInterest, PostReplyInterestDto> {

  private final PostReplyInterestRepository replyCommentInterestRepository;
  private final PostReplyReadService replyCommentReadService;

  public List<PostReplyInterestDto> findAllByInterestedReplyId(Long replyId) {
    var reply = replyCommentReadService.getById(replyId);
    return replyCommentInterestRepository.findAllByInterestedReply(reply).stream()
        .map(this::toDto).toList();
  }

  @Override
  public List<PostReplyInterestDto> findAll() {
    return replyCommentInterestRepository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public PostReplyInterestDto findById(Long id) {
    var interest = replyCommentInterestRepository.findById(id)
        .orElseThrow(NotExistReplyCommentInterestException::new);
    return toDto(interest);
  }

  @Override
  public PostReplyInterest getById(Long id) {
    return replyCommentInterestRepository.getReferenceById(id);
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
    var reply = replyCommentReadService.getById(writeRequest.contentsId());
    var userUuid = new UserUUID(writeRequest.interestingUserUuid());
    var replyInterest = PostReplyInterest.builder()
        .interestedReply(reply)
        .interestingUserUuid(userUuid)
        .dislike(writeRequest.dislike())
        .build();
    return toDto(replyCommentInterestRepository.save(replyInterest));
  }

  @Override
  public PostReplyInterestDto modify(ModifyInterestRequest modifyRequest) {
    var interest = replyCommentInterestRepository.findById(modifyRequest.id())
        .orElseThrow(NotExistReplyCommentInterestException::new);

    interest.changeCheck(modifyRequest.dislike());
    interest.changeDislike();

    return toDto(interest);
  }

  @Override
  public void deleteById(Long id) {
    replyCommentInterestRepository.deleteById(id);
  }
}
