package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyInterestRequest;
import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoReplyCommentInterest;
import com.pellto.youtoy.domain.video.dto.VideoReplyInterestDto;
import com.pellto.youtoy.domain.video.repository.VideoReplyInterestRepository;
import com.pellto.youtoy.global.exception.NotExistReplyCommentInterestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoReplyInterestService implements
    WriteUpdateDeleteService<VideoReplyInterestDto, WriteInterestRequest, ModifyInterestRequest>,
    ReadService<VideoReplyCommentInterest, VideoReplyInterestDto> {

  private final VideoReplyInterestRepository videoReplyInterestRepository;
  private final VideoReplyReadService videoReplyReadService;

  public List<VideoReplyInterestDto> findAllByInterestedReplyId(Long replyId) {
    var reply = videoReplyReadService.getById(replyId);
    return videoReplyInterestRepository.findAllByInterestedReplyComment(reply).stream()
        .map(this::toDto).toList();
  }

  @Override
  public List<VideoReplyInterestDto> findAll() {
    return videoReplyInterestRepository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public VideoReplyInterestDto findById(Long id) {
    var interest = videoReplyInterestRepository.findById(id).orElseThrow(
        NotExistReplyCommentInterestException::new);
    return toDto(interest);
  }

  @Override
  public VideoReplyCommentInterest getById(Long id) {
    return videoReplyInterestRepository.getReferenceById(id);
  }

  @Override
  public VideoReplyInterestDto toDto(VideoReplyCommentInterest entity) {
    return new VideoReplyInterestDto(
        entity.getId(),
        entity.getInterestedReplyComment().getId(),
        entity.getInterestingUserUuid().getValue(),
        entity.isDislike()
    );
  }

  @Override
  public VideoReplyInterestDto write(WriteInterestRequest writeRequest) {
    var reply = videoReplyReadService.getById(writeRequest.contentsId());
    var userUuid = new UserUUID(writeRequest.interestingUserUuid());
    var interest = VideoReplyCommentInterest.builder()
        .interestedReplyComment(reply)
        .interestingUserUuid(userUuid)
        .dislike(writeRequest.dislike())
        .build();
    return toDto(videoReplyInterestRepository.save(interest));
  }

  @Override
  public VideoReplyInterestDto modify(ModifyInterestRequest modifyRequest) {
    var interest = videoReplyInterestRepository.findById(modifyRequest.id())
        .orElseThrow(NotExistReplyCommentInterestException::new);

    interest.changeCheck(modifyRequest.dislike());
    interest.changeDislike();

    return toDto(interest);
  }

  @Override
  public void deleteById(Long id) {
    videoReplyInterestRepository.deleteById(id);
  }
}
