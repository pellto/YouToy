package com.pellto.youtoy.auth.application.adapter.out.http;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.auth.util.MemberResponseFixtureFactory;
import com.pellto.youtoy.global.dto.member.response.GetMemberInfoResponse;
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
class MemberInfoHandleAdapterTest {

  private static final String ADAPTER_NAME = "MemberInfoHandleAdapter";

  @InjectMocks
  private MemberInfoHandleAdapter memberInfoHandleAdapter;

  @Mock
  private RestTemplateAdapter restTemplateAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/getMemberInfo] getMemberInfo 성공 테스트")
  @Test
  void getMemberInfoSuccessTest() {
    var response = MemberResponseFixtureFactory.createGetMemberInfoResponse();

    given(restTemplateAdapter.getForObject(any(), any())).willReturn(response);

    var gottenMemberInfo = memberInfoHandleAdapter.getMemberInfo(response.email());

    Assertions.assertThat(gottenMemberInfo).isNotNull();
    Assertions.assertThat(gottenMemberInfo.getClass()).isEqualTo(GetMemberInfoResponse.class);
    then(restTemplateAdapter).should(times(1)).getForObject(any(), any());
  }
}