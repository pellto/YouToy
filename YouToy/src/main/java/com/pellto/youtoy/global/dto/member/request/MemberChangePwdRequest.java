package com.pellto.youtoy.global.dto.member.request;

public record MemberChangePwdRequest(
    Long id,
    String pwd,
    String repeatPwd
) {

}
