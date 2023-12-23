package com.pellto.youtoy.domain.channel.repository;

import static com.pellto.youtoy.domain.channel.util.AdminFactory.createBeforeSavedAdmin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("repository")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class AdminRepositoryTest {

  @Autowired
  private AdminRepository adminRepository;
  @Autowired
  private ChannelRepository channelRepository;

  @DisplayName("[adminRepository: save: success] 관리자 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var admin = createBeforeSavedAdmin();

    var savedAdmin = adminRepository.save(admin);

    Assertions.assertThat(savedAdmin.getId()).isNotNull();
    Assertions.assertThat(savedAdmin.getAdminUuid()).isEqualTo(admin.getAdminUuid());
    Assertions.assertThat(savedAdmin.getTargetChannel()).isEqualTo(admin.getTargetChannel());
    Assertions.assertThat(savedAdmin.getAuthLevel()).isEqualTo(admin.getAuthLevel());
  }

  @DisplayName("[adminRepository: findAll: success] 관리자 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var admin = createBeforeSavedAdmin();
    System.out.println("admin = " + admin);
    channelRepository.save(admin.getTargetChannel());
    adminRepository.save(admin);

    var foundAdmins = adminRepository.findAll();

    Assertions.assertThat(foundAdmins.size()).isEqualTo(1);
  }

  @DisplayName("[adminRepository: findById: success] id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var admin = createBeforeSavedAdmin();
    var savedAdmin = adminRepository.save(admin);

    var nullableAdmin = adminRepository.findById(savedAdmin.getId());

    Assertions.assertThat(nullableAdmin).isNotEmpty();
    var foundAdmin = nullableAdmin.get();
    Assertions.assertThat(foundAdmin).isEqualTo(savedAdmin);
  }
}