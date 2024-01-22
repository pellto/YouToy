package com.pellto.youtoy.member.application.adapter.out.persistence;

import com.pellto.youtoy.member.domain.model.Member;
import com.pellto.youtoy.member.domain.model.MemberInfo;
import com.pellto.youtoy.member.domain.model.MemberUuid;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({MemberPersistenceAdapter.class, MemberMapper.class})
@Tag("persistenceAdapter")
class MemberPersistenceAdapterTest {

  private static final String ADAPTER_NAME = "MemberPersistenceAdapterTest";

  @Autowired
  private MemberPersistenceAdapter memberPersistenceAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var birthDate = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    var uuid = new MemberUuid("test-uuid");
    var info = MemberInfo.builder()
        .email("test@email.com")
        .pwd("testPwd")
        .name("testName")
        .birthDate(birthDate)
        .build();
    var member = Member.builder()
        .uuid(uuid)
        .memberInfo(info)
        .membershipId(1L)
        .build();

    var savedMember = memberPersistenceAdapter.save(member);

    Assertions.assertThat(savedMember).isNotNull();
    Assertions.assertThat(savedMember.getId()).isNotNull();
    Assertions.assertThat(savedMember.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedMember.getMemberInfo()).usingRecursiveComparison().isEqualTo(info);
    Assertions.assertThat(savedMember.getUuid()).isEqualTo(uuid);
    Assertions.assertThat(savedMember.getMembershipId()).isEqualTo(1L);
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] 읽기 성공 테스트")
  @Test
  void loadSuccessTest() {
    var birthDate = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    var uuid = new MemberUuid("test-uuid");
    var info = MemberInfo.builder()
        .email("test@email.com")
        .pwd("testPwd")
        .name("testName")
        .birthDate(birthDate)
        .build();
    var member = Member.builder()
        .uuid(uuid)
        .memberInfo(info)
        .membershipId(1L)
        .build();
    var savedMember = memberPersistenceAdapter.save(member);

    var loadedMember = memberPersistenceAdapter.load(savedMember.getId());

    Assertions.assertThat(loadedMember).isNotNull();
    Assertions.assertThat(loadedMember).usingRecursiveComparison().isEqualTo(savedMember);
  }

  @DisplayName("[" + ADAPTER_NAME + "/delete] 삭제 성공 테스트")
  @Test
  void deleteSuccessTest() {
    var birthDate = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    var uuid = new MemberUuid("test-uuid");
    var info = MemberInfo.builder()
        .email("test@email.com")
        .pwd("testPwd")
        .name("testName")
        .birthDate(birthDate)
        .build();
    var member = Member.builder()
        .uuid(uuid)
        .memberInfo(info)
        .membershipId(1L)
        .build();
    var savedMember = memberPersistenceAdapter.save(member);

    memberPersistenceAdapter.delete(savedMember);

    Assertions.assertThatThrownBy(() -> memberPersistenceAdapter.load(savedMember.getId()))
        .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("변경 예정");
  }

  @DisplayName("[" + ADAPTER_NAME + "/update] 변경 성공 테스트")
  @Test
  void updateSuccessTest() {
    var birthDate = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
    var uuid = new MemberUuid("test-uuid");
    var info = MemberInfo.builder()
        .email("test@email.com")
        .pwd("testPwd")
        .name("testName")
        .birthDate(birthDate)
        .build();
    var member = Member.builder()
        .uuid(uuid)
        .memberInfo(info)
        .membershipId(1L)
        .build();
    var savedMember = memberPersistenceAdapter.save(member);

    savedMember.changeMemberName("changedName");
    memberPersistenceAdapter.update(savedMember);

    var updatedMember = memberPersistenceAdapter.load(savedMember.getId());

    Assertions.assertThat(updatedMember).isNotNull();
    Assertions.assertThat(updatedMember.getId()).isNotNull();
    Assertions.assertThat(updatedMember.getCreatedAt()).isNotNull();
    Assertions.assertThat(updatedMember.getMemberInfo().getName()).isEqualTo("changedName");
    Assertions.assertThat(updatedMember.getUuid()).isEqualTo(uuid);
    Assertions.assertThat(updatedMember.getMembershipId()).isEqualTo(1L);
  }

}