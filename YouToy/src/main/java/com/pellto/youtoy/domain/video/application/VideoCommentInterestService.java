package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyInterestRequest;
import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoCommentInterest;
import com.pellto.youtoy.domain.video.dto.VideoCommentInterestDto;
import com.pellto.youtoy.domain.video.repository.VideoCommentInterestRepository;
import com.pellto.youtoy.global.exception.NotExistCommentInterestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VideoCommentInterestService implements
    WriteUpdateDeleteService<VideoCommentInterestDto, WriteInterestRequest, ModifyInterestRequest>,
    ReadService<VideoCommentInterest, VideoCommentInterestDto> {

  private final VideoCommentInterestRepository videoCommentInterestRepository;
  private final VideoCommentReadService videoCommentReadService;

  public List<VideoCommentInterestDto> findAllByInterestedCommentId(Long commentId) {
    var comment = videoCommentReadService.getById(commentId);
    return videoCommentInterestRepository.findAllByInterestedComment(comment)
        .stream().map(this::toDto).toList();
  }

  @Override
  public List<VideoCommentInterestDto> findAll() {
    return videoCommentInterestRepository.findAll().stream().map(this::toDto)
        .toList();
  }

  @Override
  public VideoCommentInterestDto findById(Long id) {
    var interest = videoCommentInterestRepository.findById(id)
        .orElseThrow(NotExistCommentInterestException::new);
    return toDto(interest);
  }

  @Override
  public VideoCommentInterest getById(Long id) {
    return videoCommentInterestRepository.getReferenceById(id);
  }

  @Override
  public VideoCommentInterestDto toDto(VideoCommentInterest entity) {
    return new VideoCommentInterestDto(
        entity.getId(),
        entity.getInterestedComment().getId(),
        entity.getInterestingUserUuid().getValue(),
        entity.isDislike()
    );
  }

  @Override
  public VideoCommentInterestDto write(WriteInterestRequest writeRequest) {
    var comment = videoCommentReadService.getById(writeRequest.contentsId());
    var userUuid = new UserUUID(writeRequest.interestingUserUuid());
    var interest = VideoCommentInterest.builder()
        .interestedComment(comment)
        .interestingUserUuid(userUuid)
        .dislike(writeRequest.dislike())
        .build();
    return toDto(videoCommentInterestRepository.save(interest));
  }

  @Override
  public VideoCommentInterestDto modify(ModifyInterestRequest modifyRequest) {
    var interest = videoCommentInterestRepository.findById(modifyRequest.id())
        .orElseThrow(NotExistCommentInterestException::new);

    interest.changeCheck(modifyRequest.dislike());
    interest.changeDislike();
    
    return toDto(interest);
  }

  @Override
  public void deleteById(Long id) {
    videoCommentInterestRepository.deleteById(id);
  }
}
