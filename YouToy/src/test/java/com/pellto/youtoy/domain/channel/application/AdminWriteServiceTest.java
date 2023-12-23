package com.pellto.youtoy.domain.channel.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.channel.dto.InviteAdminRequest;
import com.pellto.youtoy.domain.channel.exception.NotExistChannelException;
import com.pellto.youtoy.domain.channel.repository.AdminRepository;
import com.pellto.youtoy.domain.channel.util.AdminFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class AdminWriteServiceTest {

  @InjectMocks
  private AdminWriteService adminWriteService;

  @Mock
  private AdminRepository adminRepository;

  @Mock
  private AdminReadService adminReadService;
  @Mock
  private ChannelReadService channelReadService;

  @DisplayName("[adminWriteService: invite: success] 관리자 초대 성공 테스트")
  @Test
  void inviteSuccessTest() {
    InviteAdminRequest req = AdminFactory.createInviteAdminRequest();
    var admin = AdminFactory.createAdmin();
    var adminDto = AdminFactory.createAdminDto();

    given(channelReadService.existById(any())).willReturn(true);
    given(channelReadService.getById(req.targetChannelId())).willReturn(admin.getTargetChannel());
    given(adminRepository.save(any())).willReturn(admin);
    given(adminReadService.toDto(admin)).willReturn(adminDto);

    var savedAdmin = adminWriteService.invite(req);

    then(channelReadService).should(times(1)).existById(any());
    then(channelReadService).should(times(1)).getById(req.targetChannelId());
    then(adminRepository).should(times(1)).save(any());
    then(adminReadService).should(times(1)).toDto(admin);
    Assertions.assertThat(savedAdmin).isEqualTo(adminDto);
  }

  @DisplayName("[adminWriteService: invite: not exist channel] 존재하지 않는 채널에 관리자 초대 실패 테스트")
  @Test
  void inviteFailWithNotExistChannelTest() {
    InviteAdminRequest req = AdminFactory.createInviteAdminRequest();

    given(channelReadService.existById(any())).willReturn(false);

    Assertions.assertThatThrownBy(
        () -> adminWriteService.invite(req)
    ).isInstanceOf(NotExistChannelException.class);
    then(channelReadService).should(times(1)).existById(any());
    then(channelReadService).should(times(0)).getById(any());
    then(adminRepository).should(times(0)).save(any());
  }

  @DisplayName("[adminWriteService: emit: success] 관리자 방출 성공 테스트")
  @Test
  void emitSuccessTest() {
    Long id = 1L;

    adminWriteService.emit(id);

    then(adminRepository).should(times(1)).deleteById(id);
  }
}