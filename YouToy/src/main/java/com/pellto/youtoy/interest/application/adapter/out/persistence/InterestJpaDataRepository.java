package com.pellto.youtoy.interest.application.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestJpaDataRepository extends JpaRepository<InterestEntity, Long> {

  void deleteAllByContentsIdAndContentsType(Long contentsId, String interestContentsType);
}
