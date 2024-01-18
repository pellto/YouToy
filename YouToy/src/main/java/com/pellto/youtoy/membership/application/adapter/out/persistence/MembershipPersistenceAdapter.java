package com.pellto.youtoy.membership.application.adapter.out.persistence;

import com.pellto.youtoy.membership.domain.model.Membership;
import com.pellto.youtoy.membership.domain.port.out.SaveMembershipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements SaveMembershipPort {

  private final MembershipMapper membershipMapper;
  private final MembershipJpaRepository jpaRepository;

  @Override
  public Membership save(Membership membership) {
    var entity = membershipMapper.toEntity(membership);
    entity = jpaRepository.save(entity);
    return membershipMapper.toDomain(entity);
  }
}
