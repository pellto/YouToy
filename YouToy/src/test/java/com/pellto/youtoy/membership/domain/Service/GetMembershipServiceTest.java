package com.pellto.youtoy.membership.domain.Service;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.membership.domain.port.out.LoadMembershipPort;
import com.pellto.youtoy.membership.util.MembershipFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class GetMembershipServiceTest {

  private static final String SERVICE_NAME = "GetMembershipService";

  @InjectMocks
  private GetMembershipService getMembershipService;

  @Mock
  private LoadMembershipPort loadMembershipPort;

  @DisplayName("[" + SERVICE_NAME + "/getMembershipInfo] getMembershipInfo 성공 테스트")
  @Test
  void getMembershipInfoSuccessTest() {
    var membership = MembershipFixtureFactory.create();

    given(loadMembershipPort.load(membership.getId())).willReturn(membership);

    getMembershipService.getMembershipInfo(membership.getId());

    then(loadMembershipPort).should(times(1)).load(membership.getId());
  }
}