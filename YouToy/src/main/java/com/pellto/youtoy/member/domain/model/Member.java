package com.pellto.youtoy.member.domain.model;

import com.pellto.youtoy.global.dto.member.MemberDto;
import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.RandomString;
import com.pellto.youtoy.global.util.Temporal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

  private final Long id;
  private final MemberUuid uuid;
  private final LocalDateTime createdAt;
  private MemberInfo memberInfo;
  private Long membershipId;

  @Builder
  public Member(Long id, MemberUuid uuid, MemberInfo memberInfo, LocalDateTime createdAt,
      Long membershipId) {
    this.id = id;
    this.uuid = General.setNullInput(uuid, this.initUuid());
    this.memberInfo = Objects.requireNonNull(memberInfo);
    this.createdAt = Temporal.createdAt(createdAt);
    this.membershipId = Objects.requireNonNull(membershipId);
  }


  private MemberUuid initUuid() {
    var uuid = String.format(
        "%s-%s-%s", RandomString.make(), RandomString.make(), RandomString.make()
    );
    return new MemberUuid(uuid);
  }

  public void changeMemberPwd(String pwd) {
    var memberInfo = MemberInfo.builder()
        .email(this.memberInfo.getEmail())
        .pwd(pwd)
        .name(this.memberInfo.getName())
        .birthDate(this.memberInfo.getBirthDate())
        .build();
    this.changeMemberInfo(memberInfo);
  }

  public void changeMemberName(String name) {
    var memberInfo = MemberInfo.builder()
        .email(this.memberInfo.getEmail())
        .pwd(this.memberInfo.getPwd())
        .name(name)
        .birthDate(this.memberInfo.getBirthDate())
        .build();
    this.changeMemberInfo(memberInfo);
  }

  public void changeMemberInfo(MemberInfo memberInfo) {
    this.memberInfo = memberInfo;
  }

  public MemberInfoDto getMemberInfoDto() {
    return this.memberInfo.toDto();
  }

  public MemberDto toDto() {
    return new MemberDto(
        this.id,
        this.uuid.value(),
        this.createdAt,
        this.memberInfo.toDto(),
        this.membershipId
    );
  }
}
