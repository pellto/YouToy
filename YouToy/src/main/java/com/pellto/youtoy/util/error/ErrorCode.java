package com.pellto.youtoy.util.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {
    // User
    PASSWORD_MISMATCH(400, "U001", "비밀번호가 일치 하지 않습니다."),
    ALREADY_EXIST_EMAIL(400, "U002", "이미 존재하는 email 입니다."),

    // Channel
    CHANNEL_DISPLAY_NAME_IS_REQUIRED(400, "C001", "채널명은 필수입니다."),
    OWNER_ID_IS_REQUIRED(400, "C002", "유저의 id는 필수입니다."),
    ALREADY_EXIST_HANDLE(400, "C003", "이미 존재하는 handle 입니다"),

    // Subscribe
    NOT_ENTERED_CHANNEL_ID(400, "S001", "구독할 채널의 id는 필수 입니다."),
    NOT_ENTERED_USER_ID(400, "S002", "구독하는 유저의 id는 필수 입니다."),
    ALREADY_SUBSCRIBED(400, "S003", "이미 채널을 구독 중입니다."),
    NOT_EXIST_SUBSCRIBE(400, "S004", "채널을 구독 중이 아닙니다.");


    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
