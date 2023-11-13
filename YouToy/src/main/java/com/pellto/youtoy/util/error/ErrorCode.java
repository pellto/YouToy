package com.pellto.youtoy.util.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {
    // User
    PASSWORD_MISMATCH(400, "U001", "비밀번호가 일치 하지 않습니다."),
    ALREADY_EXIST_EMAIL(400, "U002", "이미 존재하는 email 입니다.");
    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
