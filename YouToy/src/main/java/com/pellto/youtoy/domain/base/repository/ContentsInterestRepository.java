package com.pellto.youtoy.domain.base.repository;

import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ContentsInterestRepository<CI, ID, C> extends JpaRepository<CI, ID> {

  Optional<CI> findByInterestedContentsAndInterestingUserUuid(C interestedContents,
      UserUUID interestingUserUuid);

  List<CI> findAllByInterestedContents(C interestedContents);
}
