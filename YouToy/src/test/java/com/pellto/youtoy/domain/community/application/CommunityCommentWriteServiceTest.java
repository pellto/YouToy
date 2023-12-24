package com.pellto.youtoy.domain.community.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.community.dto.CommunityCommentDto;
import com.pellto.youtoy.domain.community.repository.CommunityCommentRepository;
import com.pellto.youtoy.domain.community.util.CommentUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class CommunityCommentWriteServiceTest {

  @InjectMocks
  private CommunityCommentWriteService commentWriteService;

  @Mock
  private CommunityCommentReadService commentReadService;
  @Mock
  private CommunityCommentRepository commentRepository;

  @DisplayName("[commentWriteService: write: success]: 댓글 작성 성공 테스트")
  @Test
  void writeSuccessTest() {
    var req = CommentUtil.createWriteCommentRequest();
    var communityComment = CommentUtil.createCommunityComment();
    var communityCommentDto = CommentUtil.createCommunityCommentDto();

    given(commentRepository.save(any())).willReturn(communityComment);
    given(commentReadService.toDto(communityComment)).willReturn(communityCommentDto);

    var writtenComment = commentWriteService.write(req);

    then(commentRepository).should(times(1)).save(any());
    then(commentReadService).should(times(1)).toDto(communityComment);
    Assertions.assertThat(writtenComment).isEqualTo(communityCommentDto);
  }

  @DisplayName("[commentWriteService: modify: success]: 댓글 수정 성공 테스트")
  @Test
  void modifySuccessTest() {
    var alreadyComment = CommentUtil.createCommunityComment();
    var changedContent = "CHANGED_CONTENT";
    var changedCommentDto = CommentUtil.createCommunityCommentDto(changedContent);
    var req = CommentUtil.createModifyCommentRequest(alreadyComment.getId(), changedContent);

    given(commentRepository.getReferenceById(req.id())).willReturn(alreadyComment);
    given(commentReadService.toDto(any())).willReturn(changedCommentDto);

    var changedComment = commentWriteService.modify(req);

    then(commentRepository).should(times(1)).getReferenceById(req.id());
    then(commentReadService).should(times(1)).toDto(any());
    Assertions.assertThat(changedComment).isNotNull();
    Assertions.assertThat(changedComment.content()).isEqualTo(changedContent);
    Assertions.assertThat(changedComment.modified()).isTrue();
    Assertions.assertThat(changedComment.getClass()).isEqualTo(CommunityCommentDto.class);
  }
}