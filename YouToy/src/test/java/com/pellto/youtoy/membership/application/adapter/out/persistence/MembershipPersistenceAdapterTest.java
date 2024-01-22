package com.pellto.youtoy.membership.application.adapter.out.persistence;

import com.pellto.youtoy.membership.domain.model.Membership;
import com.pellto.youtoy.membership.domain.model.Premium;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({MembershipPersistenceAdapter.class, MembershipMapper.class})
@Tag("persistenceAdapter")
class MembershipPersistenceAdapterTest {

  private static final String ADAPTER_NAME = "MembershipPersistenceAdapter";

  @Autowired
  private MembershipPersistenceAdapter membershipPersistenceAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var membership = Membership.builder()
        .email("test@email.com")
        .startedAt(LocalDateTime.now())
        .build();

    var savedMembership = membershipPersistenceAdapter.save(membership);

    Assertions.assertThat(savedMembership).isNotNull();
    Assertions.assertThat(savedMembership.getId()).isNotNull();
    Assertions.assertThat(savedMembership.getEmail()).isEqualTo("test@email.com");
    Assertions.assertThat(savedMembership.getExpectedExpiredAt())
        .isEqualTo(savedMembership.getStartedAt().plusDays(30));
    Assertions.assertThat(savedMembership.getPremium()).isEqualTo(Premium.NORMAL);
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] 읽기 성공 테스트")
  @Test
  void loadSuccessTest() {
    var membership = Membership.builder()
        .email("test@email.com")
        .startedAt(LocalDateTime.now())
        .build();
    var savedMembership = membershipPersistenceAdapter.save(membership);

    var loadedMembership = membershipPersistenceAdapter.load(savedMembership.getId());

    Assertions.assertThat(loadedMembership).isNotNull();
    Assertions.assertThat(loadedMembership).usingRecursiveComparison().isEqualTo(savedMembership);
  }

  @DisplayName("[" + ADAPTER_NAME + "/delete] 삭제 성공 테스트")
  @Test
  void deleteSuccessTest() {
    var membership = Membership.builder()
        .email("test@email.com")
        .startedAt(LocalDateTime.now())
        .build();
    var savedMembership = membershipPersistenceAdapter.save(membership);

    membershipPersistenceAdapter.delete(savedMembership);

    Assertions.assertThatThrownBy(() -> membershipPersistenceAdapter.load(savedMembership.getId()))
        .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("변경 예정");
  }
}