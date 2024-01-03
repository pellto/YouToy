package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.CommunityCommentInterest;
import com.pellto.youtoy.domain.community.dto.CommunityCommentInterestDto;
import com.pellto.youtoy.domain.community.dto.InterestRequest;
import com.pellto.youtoy.domain.community.repository.CommunityCommentInterestRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.exception.NotExistCommentInterestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityCommentInterestService {

  private final CommunityCommentInterestRepository commentInterestRepository;
  private final CommunityCommentReadService commentReadService;

  public CommunityCommentInterestDto interest(Long commentId, InterestRequest req) {
    var comment = commentReadService.getById(commentId);
    var userUuid = new UserUUID(req.userUuid());
    var commentInterest = CommunityCommentInterest.builder()
        .interestedCommunityComment(comment)
        .interestingUserUuid(userUuid)
        .dislike(req.dislike())
        .build();
    return toDto(commentInterestRepository.save(commentInterest));
  }

  public CommunityCommentInterestDto findByCommentIdAndUserUuid(Long commentId,
      String userUuidValue) {
    var comment = commentReadService.getById(commentId);
    var userUuid = new UserUUID(userUuidValue);
    var commentInterest = commentInterestRepository
        .findByInterestedCommunityCommentAndInterestingUserUuid(comment, userUuid)
        .orElseThrow(NotExistCommentInterestException::new);
    return toDto(commentInterest);
  }

  public List<CommunityCommentInterestDto> findAll() {
    var commentInterests = commentInterestRepository.findAll();
    return commentInterests.stream().map(this::toDto).toList();
  }

  public void deleteInterestByCommunityCommentIdAndUserUuid(Long commentId, String userUuidValue) {
    var comment = commentReadService.getById(commentId);
    var userUuid = new UserUUID(userUuidValue);
    var commentInterest = commentInterestRepository
        .findByInterestedCommunityCommentAndInterestingUserUuid(comment, userUuid)
        .orElseThrow(NotExistCommentInterestException::new);
    commentInterestRepository.deleteById(commentInterest.getId());
  }


  public CommunityCommentInterestDto toDto(CommunityCommentInterest commentInterest) {
    return new CommunityCommentInterestDto(
        commentInterest.getInterestedCommunityComment().getId(),
        commentInterest.getInterestingUserUuid().getValue(),
        commentInterest.isDislike()
    );
  }
}
