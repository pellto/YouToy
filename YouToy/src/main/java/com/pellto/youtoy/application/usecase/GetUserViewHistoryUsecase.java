package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.view.dto.ViewHistoryDto;
import com.pellto.youtoy.domain.view.service.ViewHistoryReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetUserViewHistoryUsecase {
    private final UserReadService userReadService;
    private final ViewHistoryReadService viewHistoryReadService;

    public List<ViewHistoryDto> execute(Long userId) {
        Assert.isTrue(userReadService.isExist(userId), ErrorCode.NOT_EXIST_USER.getMessage());
        return viewHistoryReadService.getByUserId(userId);
    }
}
