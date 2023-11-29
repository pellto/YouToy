package com.pellto.youtoy.util.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum VideoTypes {
    COMMENT_TYPE(null),
    VIDEO_TYPE(0),
    SHORTS_TYPE(1),
    ;
    private final Integer value;

    VideoTypes(final Integer value) {
        this.value = value;
    }
}
