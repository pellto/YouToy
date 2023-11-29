package com.pellto.youtoy.util.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {
    // User
    PASSWORD_MISMATCH(400, "U001", "비밀번호가 일치 하지 않습니다."),
    ALREADY_EXIST_EMAIL(400, "U002", "이미 존재하는 email 입니다."),
    NOT_EXIST_USER(400, "U003", "해당 유저는 존재하지 않습니다."),

    // Channel
    CHANNEL_DISPLAY_NAME_IS_REQUIRED(400, "C001", "채널명은 필수입니다."),
    OWNER_ID_IS_REQUIRED(400, "C002", "유저의 id는 필수입니다."),
    ALREADY_EXIST_HANDLE(400, "C003", "이미 존재하는 handle 입니다"),
    NOT_EXIST_CHANNEL(400, "C004", "해당 채널은 존재하지 않습니다."),

    // Subscribe
    NOT_ENTERED_CHANNEL_ID(400, "S001", "구독할 채널의 id는 필수 입니다."),
    NOT_ENTERED_USER_ID(400, "S002", "구독하는 유저의 id는 필수 입니다."),
    ALREADY_SUBSCRIBED(400, "S003", "이미 채널을 구독 중입니다."),
    NOT_EXIST_SUBSCRIBE(400, "S004", "채널을 구독 중이 아닙니다."),

    // Video
    NOT_EXIST_VIDEO(400, "V001", "Video가 존재하지 않습니다."),

    // Shorts
    NOT_EXIST_SHORT(400, "VS001", "shorts가 존재하지 않습니다."),

    // ChannelAdmin
    USER_IS_NOT_OWNER(400, "CA001", "해당 채널의 owner가 아닙니다."),
    ALREADY_EXIST_ADMIN(400, "CA002", "이미 해당 채널의 관리자 입니다."),
    NOT_EXIST_ADMIN(400, "CA003", "해당 채널의 관리자가 아닙니다."),
    NOT_AUTHORIZED_USER(400, "CA004", "해당 유저는 해당 채널의 권한이 없습니다."),

    // Comment
    NOT_EXIST_COMMENT(400, "CM001", "해당 댓글이 존재하지 않습니다."),
    // Like
    UNSUPPORTED_LIKE_CASE(400, "LK001", "지원하지 않는 like 유형입니다."),
    UNSUPPORTED_DISLIKE_CASE(400, "LK002", "지원하지 않는 dislike 유형입니다."),
    NOT_EXIST_LIKE(400, "LK003", "해당 Like는 존재하지 않습니다."),
    NOT_EXIST_DISLIKE(400, "LK004", "해당 Dislike는 존재하지 않습니다.");


    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
