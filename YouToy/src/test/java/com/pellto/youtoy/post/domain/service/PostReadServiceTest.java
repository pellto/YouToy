package com.pellto.youtoy.post.domain.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.post.domain.port.out.LoadPostPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class PostReadServiceTest {


  private static final String SERVICE_NAME = "PostReadService";

  @InjectMocks
  private PostReadService postReadService;

  @Mock
  private LoadPostPort loadPostPort;

  @DisplayName("[" + SERVICE_NAME + "/isExistPost] isExistPost 성공 테스트")
  @Test
  void isExistPostSuccessTest() {
    var postId = 1L;

    given(loadPostPort.isExistById(postId)).willReturn(true);

    var isExist = postReadService.isExistPost(postId);

    then(loadPostPort).should(times(1)).isExistById(postId);
    Assertions.assertThat(isExist).isTrue();

  }
}