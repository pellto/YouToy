package com.pellto.youtoy.interest.domain.service;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.interest.domain.model.InterestContentsType;
import com.pellto.youtoy.interest.domain.port.out.SaveInterestPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class InterestCommentServiceTest {

  private static final String SERVICE_NAME = "InterestCommentService";
  @InjectMocks
  private InterestCommentService interestCommentService;
  @Mock
  private SaveInterestPort saveInterestPort;


  @DisplayName("[" + SERVICE_NAME + "/removeAllByCommentId] removeAllByCommentId 성공 테스트")
  @Test
  void removeAllByCommentIdSuccessTest() {
    interestCommentService.removeAllByCommentId(1L);

    then(saveInterestPort).should(times(1)).deleteAllByContentsIdAndContentsType(1L,
        InterestContentsType.COMMENT.getType());
  }

}