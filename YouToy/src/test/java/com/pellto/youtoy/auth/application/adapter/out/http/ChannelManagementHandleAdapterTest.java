package com.pellto.youtoy.auth.application.adapter.out.http;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.auth.util.ChannelManagementDtoFixtureFactory;
import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import com.pellto.youtoy.global.dto.channelManagement.response.GetChannelManagementByMemberIdResponse;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("outAdapter")
class ChannelManagementHandleAdapterTest {

  private static final String ADAPTER_NAME = "ChannelManagementHandleAdapter";

  @InjectMocks
  private ChannelManagementHandleAdapter channelManagementHandleAdapter;

  @Mock
  private RestTemplateAdapter restTemplateAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/getChannelManagement] getChannelManagement 성공 테스트")
  @Test
  void getChannelManagementSuccessTest() {
    var channelManagement = ChannelManagementDtoFixtureFactory.create();
    var channelManagements = new ArrayList<ChannelManagementDto>();
    channelManagements.add(channelManagement);
    var response = new GetChannelManagementByMemberIdResponse(channelManagements);

    given(restTemplateAdapter.getForObject(any(), any())).willReturn(response);

    var gottenChannelManagement = channelManagementHandleAdapter.getChannelManagement(
        channelManagement.memberId());

    Assertions.assertThat(gottenChannelManagement).isNotNull();
    Assertions.assertThat(gottenChannelManagement.get(0).getClass())
        .isEqualTo(ChannelManagementDto.class);
    then(restTemplateAdapter).should(times(1)).getForObject(any(), any());
  }
}