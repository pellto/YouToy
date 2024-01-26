package com.pellto.youtoy.member.application.adapter.out.persistence;

import com.pellto.youtoy.global.interfaces.PersistenceAdapter;
import com.pellto.youtoy.member.domain.model.Member;
import com.pellto.youtoy.member.domain.port.out.LoadMemberPort;
import com.pellto.youtoy.member.domain.port.out.SaveMemberPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
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
  public Member loadByEmail(String email) {
    MemberEntity entity = jpaDataRepository.findByEmail(email).orElseThrow(
        () -> new IllegalArgumentException("변경 예정")
    );
    return memberMapper.toDomain(entity);
  }

  @Override
  public void delete(Member member) {
    MemberEntity entity = memberMapper.toEntity(member);
    jpaDataRepository.delete(entity);
  }

  @Override
  public Member save(Member member) {
    MemberEntity entity = memberMapper.toEntity(member);
    entity = jpaDataRepository.save(entity);
    return memberMapper.toDomain(entity);
  }

  @Override
  public void update(Member member) {
    MemberEntity entity = memberMapper.toEntity(member);
    jpaDataRepository.save(entity);
  }
}
