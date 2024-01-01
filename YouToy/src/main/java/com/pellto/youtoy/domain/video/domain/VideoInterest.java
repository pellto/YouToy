package com.pellto.youtoy.domain.video.domain;

import com.pellto.youtoy.domain.base.ContentsInterest;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "video_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class VideoInterest extends ContentsInterest<Video> {

}
