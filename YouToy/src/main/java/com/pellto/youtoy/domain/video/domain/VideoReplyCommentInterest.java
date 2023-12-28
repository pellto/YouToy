package com.pellto.youtoy.domain.video.domain;

import com.pellto.youtoy.domain.base.ReplyCommentInterest;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "video_reply_comment_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class VideoReplyCommentInterest extends ReplyCommentInterest<VideoReplyComment> {

}
