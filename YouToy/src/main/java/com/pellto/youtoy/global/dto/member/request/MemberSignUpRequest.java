package com.pellto.youtoy.global.dto.member.request;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;

public record MemberSignUpRequest(
    MemberInfoDto memberInfo,
    String repeatPwd

) {

}
