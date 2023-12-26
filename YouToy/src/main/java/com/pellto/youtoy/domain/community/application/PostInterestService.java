package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.PostInterest;
import com.pellto.youtoy.domain.community.dto.InterestRequest;
import com.pellto.youtoy.domain.community.dto.PostInterestDto;
import com.pellto.youtoy.domain.community.exception.NotExistPostInterestException;
import com.pellto.youtoy.domain.community.repository.CommunityPostInterestRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostInterestService {

  private final CommunityPostInterestRepository postInterestRepository;
  private final CommentPostReadService postReadService;

  public PostInterestDto interest(Long postId, InterestRequest req) {
    var post = postReadService.getById(postId);
    var userUuid = new UserUUID(req.userUuid());
    var postInterest = PostInterest.builder()
        .interestedPost(post)
        .interestingUserUuid(userUuid)
        .dislike(req.dislike())
        .build();
    return toDto(postInterestRepository.save(postInterest));
  }

  public PostInterestDto findByPostIdAndUserUuid(Long postId, String userUuidValue) {
    var post = postReadService.getById(postId);
    var userUuid = new UserUUID(userUuidValue);
    var postInterest = postInterestRepository
        .findByInterestedPostAndInterestingUserUuid(post, userUuid)
        .orElseThrow(NotExistPostInterestException::new);
    return toDto(postInterest);
  }

  public List<PostInterestDto> findAllByPostId(Long postId) {
    var post = postReadService.getById(postId);
    var postInterests = postInterestRepository.findAllByInterestedPost(post);
    return postInterests.stream().map(this::toDto).toList();
  }

  public void deleteInterestByPostIdAndUserUuid(Long postId, String userUuidValue) {
    var post = postReadService.getById(postId);
    var userUuid = new UserUUID(userUuidValue);
    var postInterest = postInterestRepository
        .findByInterestedPostAndInterestingUserUuid(post, userUuid)
        .orElseThrow(NotExistPostInterestException::new);
    postInterestRepository.deleteById(postInterest.getId());
  }


  public PostInterestDto toDto(PostInterest postInterest) {
    return new PostInterestDto(
        postInterest.getInterestedPost().getId(),
        postInterest.getInterestingUserUuid().getValue(),
        postInterest.isDislike()
    );
  }
}
