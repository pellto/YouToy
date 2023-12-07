package com.pellto.youtoy.domain.playlist.service;

import com.pellto.youtoy.domain.playlist.dto.CreatePlaylistCommand;
import com.pellto.youtoy.domain.playlist.dto.UpdatePlaylistCommand;
import com.pellto.youtoy.domain.playlist.entity.Playlist;
import com.pellto.youtoy.domain.playlist.repository.PlaylistRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class PlaylistWriteService {

  private final PlaylistRepository playlistRepository;

  public Playlist create(CreatePlaylistCommand cmd) {
    var playlist = Playlist
        .builder()
        .channelId(cmd.channelId())
        .title(cmd.title())
        .targetRange(cmd.targetRange())
        .build();

    return playlistRepository.save(playlist);
  }

  public void update(UpdatePlaylistCommand cmd) {
    boolean changeChecker = false;
    // TODO: change all if-throw statement to Assertion
    Assert.notNull(cmd.id(), ErrorCode.NOT_ENTERED_PLAYLIST_ID.getMessage());
    var playlist = playlistRepository.findById(cmd.id()).orElse(null);
    Assert.notNull(playlist, ErrorCode.NOT_EXIST_PLAYLIST.getMessage());

    if (cmd.title() != null && !cmd.title().equals(playlist.getTitle())) {
      playlist.setTitle(cmd.title());
      changeChecker = true;
    }
    if (cmd.targetRange() != null && !Objects.equals(cmd.targetRange(),
        playlist.getTargetRange())) {
      playlist.setTargetRange(cmd.targetRange());
      changeChecker = true;
    }
    if (changeChecker) {
      playlistRepository.save(playlist);
    }
  }
}
