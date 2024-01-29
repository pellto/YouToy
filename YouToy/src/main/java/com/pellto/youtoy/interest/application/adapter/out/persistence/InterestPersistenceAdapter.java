package com.pellto.youtoy.interest.application.adapter.out.persistence;

import com.pellto.youtoy.global.interfaces.PersistenceAdapter;
import com.pellto.youtoy.interest.domain.model.Interest;
import com.pellto.youtoy.interest.domain.port.out.LoadInterestPort;
import com.pellto.youtoy.interest.domain.port.out.SaveInterestPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class InterestPersistenceAdapter implements LoadInterestPort, SaveInterestPort {

  private final InterestMapper interestMapper;
  private final InterestJpaDataRepository jpaDataRepository;


  @Override
  public void delete(Interest interest) {

  }

  @Override
  public Interest save(Interest interest) {
    var entity = interestMapper.toEntity(interest);
    return interestMapper.toDomain(jpaDataRepository.save(entity));
  }

  @Override
  public Interest load(Long id) {
    return null;
  }
}
