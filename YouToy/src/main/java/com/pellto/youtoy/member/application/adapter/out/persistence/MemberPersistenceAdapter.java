package com.pellto.youtoy.member.application.adapter.out.persistence;

import com.pellto.youtoy.member.domain.model.Member;
import com.pellto.youtoy.member.domain.port.out.LoadMemberPort;
import com.pellto.youtoy.member.domain.port.out.SaveMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements SaveMemberPort, LoadMemberPort {

  private final MemberMapper memberMapper;
  private final MemberJpaDataRepository jpaDataRepository;

  @Override
  public Member load(Long id) {
    MemberEntity entity = jpaDataRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("변경 예정")
    );
    return memberMapper.toDomain(entity);
  }

  @Override
  public Member save(Member member) {
    MemberEntity entity = memberMapper.toEntity(member);
    entity = jpaDataRepository.save(entity);
    return memberMapper.toDomain(entity);
  }
}
