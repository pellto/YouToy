package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.CommunityReplyCommentInterest;
import com.pellto.youtoy.domain.community.dto.CommunityReplyCommentInterestDto;
import com.pellto.youtoy.domain.community.dto.InterestRequest;
import com.pellto.youtoy.domain.community.repository.CommunityReplyCommentInterestRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.exception.NotExistReplyCommentInterestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityReplyCommentInterestService {

  private final CommunityReplyCommentInterestRepository replyCommentInterestRepository;
  private final PostReplyCommentReadService replyCommentReadService;

  public CommunityReplyCommentInterestDto interest(Long replyCommentId, InterestRequest req) {
    var replyComment = replyCommentReadService.getById(replyCommentId);
    var userUuid = new UserUUID(req.userUuid());
    var replyCommentInterest = CommunityReplyCommentInterest.builder()
        .interestedReplyComment(replyComment)
        .interestingUserUuid(userUuid)
        .dislike(req.dislike())
        .build();
    return toDto(replyCommentInterestRepository.save(replyCommentInterest));
  }

  public CommunityReplyCommentInterestDto findByReplyCommentIdAndUserUuid(Long replyCommentId,
      String userUuidValue) {
    var replyComment = replyCommentReadService.getById(replyCommentId);
    var userUuid = new UserUUID(userUuidValue);
    var replyCommentInterest = replyCommentInterestRepository
        .findByInterestedReplyCommentAndInterestingUserUuid(replyComment, userUuid)
        .orElseThrow(NotExistReplyCommentInterestException::new);
    return toDto(replyCommentInterest);
  }

  public List<CommunityReplyCommentInterestDto> findAll() {
    var replyCommentInterests = replyCommentInterestRepository.findAll();
    return replyCommentInterests.stream().map(this::toDto).toList();
  }

  public void deleteInterestByCommunityReplyCommentIdAndUserUuid(Long replyCommentId,
      String userUuidValue) {
    var replyComment = replyCommentReadService.getById(replyCommentId);
    var userUuid = new UserUUID(userUuidValue);
    var replyCommentInterest = replyCommentInterestRepository
        .findByInterestedReplyCommentAndInterestingUserUuid(replyComment, userUuid)
        .orElseThrow(NotExistReplyCommentInterestException::new);
    replyCommentInterestRepository.deleteById(replyCommentInterest.getId());
  }


  public CommunityReplyCommentInterestDto toDto(
      CommunityReplyCommentInterest replyCommentInterest) {
    return new CommunityReplyCommentInterestDto(
        replyCommentInterest.getInterestedReplyComment().getId(),
        replyCommentInterest.getInterestingUserUuid().getValue(),
        replyCommentInterest.isDislike()
    );
  }
}
