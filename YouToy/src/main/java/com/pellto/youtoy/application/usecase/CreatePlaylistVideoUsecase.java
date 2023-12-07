package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.playlist.dto.CreatePlaylistVideoCommand;
import com.pellto.youtoy.domain.playlist.dto.PlaylistVideoDto;
import com.pellto.youtoy.domain.playlist.service.PlaylistReadService;
import com.pellto.youtoy.domain.playlist.service.PlaylistVideoWriteService;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.types.VideoTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@RequiredArgsConstructor
@Service
public class CreatePlaylistVideoUsecase {

  private final PlaylistVideoWriteService playlistVideoWriteService;
  private final PlaylistReadService playlistReadService;
  private final VideoReadService videoReadService;
  private final ShortReadService shortReadService;

  public PlaylistVideoDto execute(CreatePlaylistVideoCommand cmd) {
    if (cmd.videoType().equals(VideoTypes.VIDEO_TYPE.getValue())) {
      Assert.isTrue(
          videoReadService.existVideo(cmd.videoId()),
          ErrorCode.NOT_EXIST_VIDEO.getMessage()
      );
    } else if (cmd.videoType().equals(VideoTypes.SHORTS_TYPE.getValue())) {
      Assert.isTrue(
          shortReadService.existShort(cmd.videoId()),
          ErrorCode.NOT_EXIST_SHORT.getMessage()
      );
    } else {
      throw new UnsupportedOperationException(ErrorCode.UNSUPPORTED_VIDEO_TYPE.getMessage());
    }
    Assert.isTrue(
        playlistReadService.existById(cmd.playlistId()),
        ErrorCode.NOT_EXIST_PLAYLIST.getMessage()
    );
    return playlistVideoWriteService.create(cmd);
  }
}
