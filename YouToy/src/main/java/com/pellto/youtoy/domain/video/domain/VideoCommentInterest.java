package com.pellto.youtoy.domain.video.domain;

import com.pellto.youtoy.domain.base.CommentInterest;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "video_comment_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class VideoCommentInterest extends CommentInterest<VideoComment> {

}
