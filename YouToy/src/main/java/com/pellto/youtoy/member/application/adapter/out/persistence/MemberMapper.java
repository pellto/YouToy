package com.pellto.youtoy.member.application.adapter.out.persistence;

import com.pellto.youtoy.member.domain.model.Member;
import com.pellto.youtoy.member.domain.model.MemberInfo;
import com.pellto.youtoy.member.domain.model.MemberUuid;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

  public Member toDomain(MemberEntity entity) {
    var info = new MemberInfo(
        entity.getEmail(),
        entity.getPwd(),
        entity.getName(),
        entity.getBirthDate()
    );
    var uuid = new MemberUuid(entity.getMemberUuid());

    return Member.builder()
        .id(entity.getId())
        .uuid(uuid)
        .memberInfo(info)
        .membershipId(entity.getMembershipId())
        .createdAt(entity.getCreatedAt())
        .build();
  }

  public MemberEntity toEntity(Member member) {
    return MemberEntity.builder()
        .id(member.getId())
        .memberUuid(member.getUuid().value())
        .email(member.getMemberInfo().getEmail())
        .pwd(member.getMemberInfo().getPwd())
        .name(member.getMemberInfo().getName())
        .birthDate(member.getMemberInfo().getBirthDate())
        .membershipId(member.getMembershipId())
        .createdAt(member.getCreatedAt())
        .build();
  }
}
