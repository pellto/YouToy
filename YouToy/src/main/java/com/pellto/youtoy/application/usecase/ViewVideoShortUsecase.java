package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.view.dto.CreateViewHistoryCommand;
import com.pellto.youtoy.domain.view.service.ShortWriteService;
import com.pellto.youtoy.domain.view.service.VideoWriteService;
import com.pellto.youtoy.domain.view.service.ViewHistoryReadService;
import com.pellto.youtoy.domain.view.service.ViewHistoryWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.types.VideoTypes;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
@Transactional
public class ViewVideoShortUsecase {

  private final UserReadService userReadService;
  private final VideoWriteService videoWriteService;
  private final ShortWriteService shortWriteService;
  private final ViewHistoryWriteService viewHistoryWriteService;
  private final ViewHistoryReadService viewHistoryReadService;

  public void execute(CreateViewHistoryCommand cmd) {
    Assert.isTrue(userReadService.isExist(cmd.userId()), ErrorCode.NOT_EXIST_USER.getMessage());
    var diffDays = 7L;
    if (viewHistoryReadService.existByCreateCommand(cmd)) {
      var lastViewHistoryDto = viewHistoryReadService.getByCreateCommand(cmd);
      diffDays = Duration.between(lastViewHistoryDto.createdAt(), LocalDateTime.now()).toDays();
      viewHistoryWriteService.deleteByCreateCommand(cmd);
    }
    if (cmd.videoType().equals(VideoTypes.VIDEO_TYPE.getValue())) {
      // is Video
      if (diffDays >= 7L) {
        videoWriteService.increaseViewCount(cmd.videoId());
      }
    } else if (cmd.videoType().equals(VideoTypes.SHORTS_TYPE.getValue())) {
      // is Shorts
      if (diffDays >= 7L) {
        shortWriteService.increaseViewCount(cmd.videoId());
      }
    } else {
      throw new UnsupportedOperationException(ErrorCode.UNSUPPORTED_VIDEO_TYPE.getMessage());
    }
    viewHistoryWriteService.create(cmd);
  }
}
