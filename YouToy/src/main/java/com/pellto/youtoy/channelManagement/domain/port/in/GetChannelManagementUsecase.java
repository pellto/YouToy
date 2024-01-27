package com.pellto.youtoy.channelManagement.domain.port.in;

import com.pellto.youtoy.global.dto.channelManagement.response.GetChannelManagementByMemberIdResponse;

public interface GetChannelManagementUsecase {

  GetChannelManagementByMemberIdResponse getByMemberId(Long memberId);
}
