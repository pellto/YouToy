package com.pellto.youtoy.domain.view.service;

import com.pellto.youtoy.domain.view.dto.CreateViewHistoryCommand;
import com.pellto.youtoy.domain.view.dto.ViewHistoryDto;
import com.pellto.youtoy.domain.view.entity.ViewHistory;
import com.pellto.youtoy.domain.view.repository.ViewHistoryRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class ViewHistoryReadService {

  private final ViewHistoryRepository viewHistoryRepository;

  public boolean existByCreateCommand(CreateViewHistoryCommand cmd) {
    return viewHistoryRepository.existByUserIdVideoIdVideoType(cmd.userId(), cmd.videoId(),
        cmd.videoType());
  }

  public ViewHistoryDto getByCreateCommand(CreateViewHistoryCommand cmd) {
    return toDto(viewHistoryRepository.findByUserIdVideoIdVideoType(
        cmd.userId(), cmd.videoId(), cmd.videoType()
    ).orElseThrow(
        () -> new UnsupportedOperationException(ErrorCode.NOT_EXIST_VIEW_HISTORY.getMessage())
    ));
  }

  public ViewHistoryDto getById(Long id) {
    return toDto(viewHistoryRepository.findById(id)
        .orElseThrow(
            () -> new UnsupportedOperationException(ErrorCode.NOT_EXIST_VIEW_HISTORY.getMessage())
        )
    );
  }

  public List<ViewHistoryDto> getByUserId(Long userId) {
    var viewHistories = viewHistoryRepository.getByUserId(userId);
    Assert.notEmpty(viewHistories, ErrorCode.NOT_EXIST_USER_VIEW_HISTORY.getMessage());
    return viewHistories.stream().map(this::toDto).toList();
  }

  public ViewHistoryDto toDto(ViewHistory viewHistory) {
    return new ViewHistoryDto(
        viewHistory.getId(),
        viewHistory.getUserId(),
        viewHistory.getVideoId(),
        viewHistory.getVideoType(),
        viewHistory.getLastViewAt(),
        viewHistory.getCreatedAt()
    );
  }
}
