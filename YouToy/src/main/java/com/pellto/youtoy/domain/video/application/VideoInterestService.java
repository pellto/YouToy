package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.base.dto.ModifyInterestRequest;
import com.pellto.youtoy.domain.base.dto.WriteInterestRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoInterest;
import com.pellto.youtoy.domain.video.dto.VideoInterestDto;
import com.pellto.youtoy.domain.video.repository.VideoInterestRepository;
import com.pellto.youtoy.global.exception.NotExistContentsInterestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoInterestService implements
    ReadService<VideoInterest, VideoInterestDto>,
    WriteUpdateDeleteService<VideoInterestDto, WriteInterestRequest, ModifyInterestRequest> {

  private final VideoInterestRepository videoInterestRepository;

  private final VideoReadService contentsReadService;

  @Override
  public List<VideoInterestDto> findAll() {
    return videoInterestRepository.findAll().stream().map(this::toDto).toList();
  }

  public List<VideoInterestDto> findAllByContentsId(Long contentsId) {
    var contents = contentsReadService.getById(contentsId);
    return videoInterestRepository.findAllByInterestedContents(contents).stream().map(this::toDto)
        .toList();
  }

  @Override
  public VideoInterestDto findById(Long id) {
    var interest = videoInterestRepository.findById(id).orElseThrow(
        NotExistContentsInterestException::new);
    return toDto(interest);
  }

  @Override
  public VideoInterest getById(Long id) {
    return videoInterestRepository.getReferenceById(id);
  }

  @Override
  public VideoInterestDto toDto(VideoInterest entity) {
    return new VideoInterestDto(entity.getId(), entity.getInterestedContents().getId(),
        entity.getInterestingUserUuid().getValue(),
        entity.isDislike());
  }

  @Override
  public VideoInterestDto write(WriteInterestRequest writeRequest) {
    var contents = contentsReadService.getById(writeRequest.contentsId());
    var interest = VideoInterest.builder()
        .interestedContents(contents)
        .interestingUserUuid(new UserUUID(writeRequest.interestingUserUuid()))
        .dislike(writeRequest.dislike())
        .build();
    return toDto(videoInterestRepository.save(interest));
  }

  @Override
  public VideoInterestDto modify(ModifyInterestRequest modifyRequest) {
    var interest = videoInterestRepository.findById(modifyRequest.id())
        .orElseThrow(NotExistContentsInterestException::new);

    interest.changeCheck(modifyRequest.dislike());
    interest.changeDislike();

    return toDto(interest);
  }

  @Override
  public void deleteById(Long id) {
    videoInterestRepository.deleteById(id);
  }
}
