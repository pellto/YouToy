package com.pellto.youtoy.domain.video.domain;

import com.pellto.youtoy.domain.base.Contents;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "video")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Video extends Contents {

}
