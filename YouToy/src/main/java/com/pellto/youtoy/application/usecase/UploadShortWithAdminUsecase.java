package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.video.dto.UploadShortCommand;
import com.pellto.youtoy.domain.video.entity.Shorts;
import com.pellto.youtoy.domain.video.service.ShortWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UploadShortWithAdminUsecase {
    private final ShortWriteService shortWriteService;
    private final AdminAuthorizeUsecase adminAuthorizeUsecase;

    // TODO: entity to dto in usecase
    public Shorts execute(UploadShortCommand cmd) {
        if (adminAuthorizeUsecase.execute(cmd))
            return shortWriteService.upload(cmd);
        return null;
    }
}
