package com.pellto.youtoy.domain.channel.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.channel.domain.Admin;
import com.pellto.youtoy.domain.channel.dto.AdminDto;
import com.pellto.youtoy.domain.channel.exception.NotExistAdminException;
import com.pellto.youtoy.domain.channel.repository.AdminRepository;
import com.pellto.youtoy.domain.channel.util.AdminFactory;
import java.util.ArrayList;
import java.util.Optional;
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
class AdminReadServiceTest {

  @InjectMocks
  private AdminReadService adminReadService;

  @Mock
  private AdminRepository adminRepository;

  @DisplayName("[adminReadService: findAll: success] 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var admin = AdminFactory.createAdmin();
    var adminList = new ArrayList<Admin>();
    adminList.add(admin);

    given(adminRepository.findAll()).willReturn(adminList);

    var foundAdmins = adminReadService.findAll();

    then(adminRepository).should(times(1)).findAll();
    Assertions.assertThat(foundAdmins).isNotNull();
    Assertions.assertThat(foundAdmins.size()).isEqualTo(adminList.size());
    Assertions.assertThat(foundAdmins.get(0).getClass()).isEqualTo(AdminDto.class);
  }

  @DisplayName("[adminReadService: findById: success] id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var admin = AdminFactory.createAdmin();

    given(adminRepository.findById(admin.getId())).willReturn(Optional.of(admin));

    var foundAdmin = adminReadService.findById(admin.getId());

    then(adminRepository).should(times(1)).findById(admin.getId());
    Assertions.assertThat(foundAdmin).isNotNull();
    Assertions.assertThat(foundAdmin.getClass()).isEqualTo(AdminDto.class);
  }

  @DisplayName("[adminReadService: findById: not exist admin] 존재하지 않는 admin id 조건 조회 성공 테스트")
  @Test
  void findByIdFailWithNotExistAdminTest() {
    given(adminRepository.findById(any())).willReturn(Optional.empty());

    Assertions.assertThatThrownBy(
        () -> adminReadService.findById(any())
    ).isInstanceOf(NotExistAdminException.class);
    then(adminRepository).should(times(1)).findById(any());
  }
}