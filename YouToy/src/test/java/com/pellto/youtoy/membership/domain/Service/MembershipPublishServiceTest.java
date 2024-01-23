package com.pellto.youtoy.membership.domain.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.member.util.MemberFixtureFactory;
import com.pellto.youtoy.member.util.MemberInfoFixtureFactory;
import com.pellto.youtoy.membership.domain.port.out.LoadMembershipPort;
import com.pellto.youtoy.membership.domain.port.out.MembershipEventPort;
import com.pellto.youtoy.membership.domain.port.out.SaveMembershipPort;
import com.pellto.youtoy.membership.util.MembershipFixtureFactory;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class MembershipPublishServiceTest {

  private static final String SERVICE_NAME = "MembershipPublishService";

  @InjectMocks
  private MembershipPublishService membershipPublishService;
  @Mock
  private MembershipEventPort membershipEventPort;
  @Mock
  private SaveMembershipPort saveMembershipPort;
  @Mock
  private LoadMembershipPort loadMembershipPort;

  @DisplayName("[" + SERVICE_NAME + "/publish] publish 성공 테스트")
  @Test
  void publishSuccessTest() {
    var memberInfoDto = MemberInfoFixtureFactory.create().toDto();
    var requestedAt = LocalDateTime.now();
    var membership = MembershipFixtureFactory.create();

    given(saveMembershipPort.save(any())).willReturn(membership);

    membershipPublishService.publish(memberInfoDto, requestedAt);

    then(saveMembershipPort).should(times(1)).save(any());
    then(membershipEventPort).should(times(1))
        .publishedInitMembership(membership.getId(), memberInfoDto);
  }

  @DisplayName("[" + SERVICE_NAME + "/remove] remove 성공 테스트")
  @Test
  void removeSuccessTest() {
    var memberDto = MemberFixtureFactory.create().toDto();
    var membership = MembershipFixtureFactory.create(memberDto.id());

    given(loadMembershipPort.load(memberDto.id())).willReturn(membership);

    membershipPublishService.remove(memberDto);

    then(loadMembershipPort).should(times(1)).load(memberDto.id());
    then(saveMembershipPort).should(times(1)).delete(membership);
    then(membershipEventPort).should(times(1)).membershipRemovedEvent(any());
  }
}