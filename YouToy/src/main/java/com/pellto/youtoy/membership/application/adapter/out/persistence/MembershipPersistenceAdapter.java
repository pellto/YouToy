package com.pellto.youtoy.membership.application.adapter.out.persistence;

import com.pellto.youtoy.membership.domain.model.Membership;
import com.pellto.youtoy.membership.domain.port.out.LoadMembershipPort;
import com.pellto.youtoy.membership.domain.port.out.SaveMembershipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements SaveMembershipPort, LoadMembershipPort {

  private final MembershipMapper membershipMapper;
  private final MembershipJpaRepository jpaRepository;


  @Override
  public Membership load(Long id) {
    var entity = jpaRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("변경 예정")
    );
    return membershipMapper.toDomain(entity);
  }


  @Override
  public void delete(Membership membership) {
    var entity = membershipMapper.toEntity(membership);
    jpaRepository.delete(entity);
  }

  @Override
  public Membership save(Membership membership) {
    var entity = membershipMapper.toEntity(membership);
    entity = jpaRepository.save(entity);
    return membershipMapper.toDomain(entity);
  }
}
