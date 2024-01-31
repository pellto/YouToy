package com.pellto.youtoy.interest.application.adapter.out.persistence;

import com.pellto.youtoy.interest.util.InterestFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({InterestPersistenceAdapter.class, InterestMapper.class})
@Tag("persistenceAdapter")
class InterestPersistenceAdapterTest {

  private static final String ADAPTER_NAME = "InterestPersistenceAdapter";

  @Autowired
  private InterestPersistenceAdapter interestPersistenceAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var beforeSaved = InterestFixtureFactory.createBeforeSaved();
    var interest = InterestFixtureFactory.create();

    var savedInterest = interestPersistenceAdapter.save(beforeSaved);

    Assertions.assertThat(savedInterest).isNotNull();
    Assertions.assertThat(savedInterest.getId()).isNotNull();
    Assertions.assertThat(savedInterest.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedInterest).usingRecursiveComparison()
        .ignoringFields("id", "createdAt").isEqualTo(interest);
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] 읽기 성공 테스트")
  @Test
  void loadSuccessTest() {
    var beforeSaved = InterestFixtureFactory.createBeforeSaved();
    var savedInterest = interestPersistenceAdapter.save(beforeSaved);

    var loadedInterest = interestPersistenceAdapter.load(savedInterest.getId());

    Assertions.assertThat(loadedInterest).isNotNull();
    Assertions.assertThat(loadedInterest.getId()).isNotNull();
    Assertions.assertThat(loadedInterest.getCreatedAt()).isNotNull();
    Assertions.assertThat(loadedInterest).usingRecursiveComparison().isEqualTo(savedInterest);
  }

  @DisplayName("[" + ADAPTER_NAME + "/delete] 삭제 성공 테스트")
  @Test
  void deleteSuccessTest() {
    var beforeSaved = InterestFixtureFactory.createBeforeSaved();
    var savedInterest = interestPersistenceAdapter.save(beforeSaved);
    var beforeDeletedInterest = interestPersistenceAdapter.load(savedInterest.getId());

    interestPersistenceAdapter.delete(beforeDeletedInterest);

    Assertions.assertThatThrownBy(() -> interestPersistenceAdapter.load(savedInterest.getId()))
        .isInstanceOf(
            IllegalArgumentException.class).hasMessage("interest 없음");
  }
}