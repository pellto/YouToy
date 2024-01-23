package com.pellto.youtoy.member.domain.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.member.domain.port.out.LoadMemberPort;
import com.pellto.youtoy.member.domain.port.out.MembershipHandlePort;
import com.pellto.youtoy.member.util.MemberFixtureFactory;
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
class GetMemberDetailServiceTest {

  private static final String SERVICE_NAME = "GetMemberDetailService";
  @InjectMocks
  private GetMemberDetailService getMemberDetailService;
  @Mock
  private LoadMemberPort loadMemberPort;
  @Mock
  private MembershipHandlePort membershipHandlePort;

  @DisplayName("[" + SERVICE_NAME + "/getMemberDetail] getMemberDetail 성공 테스트")
  @Test
  void getMemberDetailSuccessTest() {
    var member = MemberFixtureFactory.create();
    var membershipDto = MembershipFixtureFactory.create().toDto();

    given(loadMemberPort.load(member.getId())).willReturn(member);
    given(membershipHandlePort.getMembershipInfo(member.getMembershipId())).willReturn(
        membershipDto);

    getMemberDetailService.getMemberDetail(member.getId());

    then(loadMemberPort).should(times(1)).load(member.getId());
    then(membershipHandlePort).should(times(1)).getMembershipInfo(member.getMembershipId());

  }
}