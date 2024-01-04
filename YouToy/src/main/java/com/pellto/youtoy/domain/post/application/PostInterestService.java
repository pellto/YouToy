package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyInterestRequest;
import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.post.domain.PostInterest;
import com.pellto.youtoy.domain.post.dto.PostInterestDto;
import com.pellto.youtoy.domain.post.repository.PostInterestRepository;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.exception.NotExistContentsInterestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostInterestService implements ReadService<PostInterest, PostInterestDto>,
    WriteUpdateDeleteService<PostInterestDto, WriteInterestRequest, ModifyInterestRequest> {

  private final PostInterestRepository postInterestRepository;
  private final PostReadService postReadService;

  public List<PostInterestDto> findAllByContentsId(Long contentsId) {
    var contents = postReadService.getById(contentsId);
    return postInterestRepository.findAllByInterestedContents(contents).stream().map(this::toDto)
        .toList();
  }

  @Override
  public List<PostInterestDto> findAll() {
    return postInterestRepository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public PostInterestDto findById(Long id) {
    var interest = postInterestRepository.findById(id).orElseThrow(
        NotExistContentsInterestException::new);
    return toDto(interest);
  }

  @Override
  public PostInterest getById(Long id) {
    return postInterestRepository.getReferenceById(id);
  }

  @Override
  public PostInterestDto toDto(PostInterest postInterest) {
    return new PostInterestDto(
        postInterest.getId(),
        postInterest.getInterestedContents().getId(),
        postInterest.getInterestingUserUuid().getValue(),
        postInterest.isDislike()
    );
  }

  @Override
  public PostInterestDto write(WriteInterestRequest writeRequest) {
    var post = postReadService.getById(writeRequest.contentsId());
    var userUuid = new UserUUID(writeRequest.interestingUserUuid());
    var postInterest = PostInterest.builder()
        .interestedContents(post)
        .interestingUserUuid(userUuid)
        .dislike(writeRequest.dislike())
        .build();
    return toDto(postInterestRepository.save(postInterest));
  }

  @Override
  public PostInterestDto modify(ModifyInterestRequest modifyRequest) {
    var interest = postInterestRepository.findById(modifyRequest.id())
        .orElseThrow(NotExistContentsInterestException::new);

    interest.changeCheck(modifyRequest.dislike());
    interest.changeDislike();

    return toDto(interest);
  }

  @Override
  public void deleteById(Long id) {
    postInterestRepository.deleteById(id);
  }
}
