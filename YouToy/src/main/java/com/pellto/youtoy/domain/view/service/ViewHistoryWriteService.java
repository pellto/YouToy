package com.pellto.youtoy.domain.view.service;

import com.pellto.youtoy.domain.view.dto.CreateViewHistoryCommand;
import com.pellto.youtoy.domain.view.entity.ViewHistory;
import com.pellto.youtoy.domain.view.repository.ViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ViewHistoryWriteService {

  private final ViewHistoryRepository viewHistoryRepository;

  public ViewHistory create(CreateViewHistoryCommand cmd) {
    ViewHistory viewHistory = ViewHistory.builder()
        .userId(cmd.userId())
        .videoId(cmd.videoId())
        .videoType(cmd.videoType())
        .lastViewAt(cmd.lastViewAt())
        .build();
    return viewHistoryRepository.save(viewHistory);
  }

  public void deleteByCreateCommand(CreateViewHistoryCommand cmd) {
    viewHistoryRepository.deleteByUserIdVideoIdVideoType(cmd.userId(), cmd.videoId(),
        cmd.videoType());
  }
}
