package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.view.dto.UpdateShortCommand;
import com.pellto.youtoy.domain.view.entity.Shorts;
import com.pellto.youtoy.domain.view.service.ShortWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateShortWithAdminUsecase {

  private final ShortWriteService shortWriteService;
  private final AdminAuthorizeUsecase adminAuthorizeUsecase;

  // TODO: entity to dto in usecase
  public Shorts execute(UpdateShortCommand cmd) {
    if (adminAuthorizeUsecase.execute(cmd)) {
      return shortWriteService.update(cmd);
    }
    return null;
  }
}
