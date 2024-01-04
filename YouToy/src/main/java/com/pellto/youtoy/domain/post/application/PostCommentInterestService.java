package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyInterestRequest;
import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.post.domain.PostCommentInterest;
import com.pellto.youtoy.domain.post.dto.PostCommentInterestDto;
import com.pellto.youtoy.domain.post.repository.PostCommentInterestRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.exception.NotExistCommentInterestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentInterestService implements
    ReadService<PostCommentInterest, PostCommentInterestDto>,
    WriteUpdateDeleteService<PostCommentInterestDto, WriteInterestRequest, ModifyInterestRequest> {

  private final PostCommentInterestRepository commentInterestRepository;
  private final PostCommentReadService commentReadService;

  public List<PostCommentInterestDto> findAllByInterestedCommentId(Long commentId) {
    var comment = commentReadService.getById(commentId);
    return commentInterestRepository.findAllByInterestedComment(comment).stream().map(this::toDto)
        .toList();
  }

  @Override
  public List<PostCommentInterestDto> findAll() {
    var commentInterests = commentInterestRepository.findAll();
    return commentInterests.stream().map(this::toDto).toList();
  }

  @Override
  public PostCommentInterestDto findById(Long id) {
    var interest = commentInterestRepository.findById(id)
        .orElseThrow(NotExistCommentInterestException::new);
    return toDto(interest);
  }

  @Override
  public PostCommentInterest getById(Long id) {
    return commentInterestRepository.getReferenceById(id);
  }

  @Override
  public PostCommentInterestDto toDto(PostCommentInterest commentInterest) {
    return new PostCommentInterestDto(
        commentInterest.getId(),
        commentInterest.getInterestedComment().getId(),
        commentInterest.getInterestingUserUuid().getValue(),
        commentInterest.isDislike()
    );
  }

  @Override
  public PostCommentInterestDto write(WriteInterestRequest writeRequest) {
    var comment = commentReadService.getById(writeRequest.contentsId());
    var userUuid = new UserUUID(writeRequest.interestingUserUuid());
    var commentInterest = PostCommentInterest.builder()
        .interestedComment(comment)
        .interestingUserUuid(userUuid)
        .dislike(writeRequest.dislike())
        .build();
    return toDto(commentInterestRepository.save(commentInterest));
  }

  @Override
  public PostCommentInterestDto modify(ModifyInterestRequest modifyRequest) {
    var interest = commentInterestRepository.findById(modifyRequest.id())
        .orElseThrow(NotExistCommentInterestException::new);

    interest.changeCheck(modifyRequest.dislike());
    interest.changeDislike();

    return toDto(interest);
  }

  @Override
  public void deleteById(Long id) {
    commentInterestRepository.deleteById(id);
  }
}
