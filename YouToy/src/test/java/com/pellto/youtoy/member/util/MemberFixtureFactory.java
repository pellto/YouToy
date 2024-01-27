package com.pellto.youtoy.member.util;

import com.pellto.youtoy.global.dto.member.request.MemberChangeNameRequest;
import com.pellto.youtoy.global.dto.member.request.MemberChangePwdRequest;
import com.pellto.youtoy.global.dto.member.request.MemberSignUpRequest;
import com.pellto.youtoy.member.domain.model.Member;
import com.pellto.youtoy.member.domain.model.MemberInfo;
import com.pellto.youtoy.member.domain.model.MemberUuid;
import java.time.LocalDateTime;

public class MemberFixtureFactory {

  private static final Long ID = 1L;
  private static final MemberUuid MEMBER_UUID = new MemberUuid("uuid");
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final MemberInfo MEMBER_INFO = MemberInfoFixtureFactory.create();
  private static final Long MEMBERSHIP_ID = 1L;

  public static Member create(Long id) {
    return Member.builder()
        .id(id)
        .uuid(MEMBER_UUID)
        .createdAt(CREATED_AT)
        .memberInfo(MEMBER_INFO)
        .membershipId(MEMBERSHIP_ID)
        .build();
  }

  public static MemberSignUpRequest createSignUpRequest() {
    return new MemberSignUpRequest(MEMBER_INFO.toDto(ID), MEMBER_INFO.getPwd());
  }

  public static MemberSignUpRequest createSignUpWrongPwdRequest() {
    return new MemberSignUpRequest(MEMBER_INFO.toDto(ID), "WRONG_PWD");
  }

  public static MemberChangePwdRequest createChangePwdRequest() {
    return new MemberChangePwdRequest(ID, "CHANGED_PWD", "CHANGED_PWD");
  }

  public static MemberChangePwdRequest createChangeWrongPwdRequest() {
    return new MemberChangePwdRequest(ID, "CHANGED_PWD", "WRONG_CHANGED_PWD");
  }

  public static MemberChangeNameRequest createChangeNameRequest() {
    return new MemberChangeNameRequest(ID, "CHANGED_NAME");
  }

  public static Member createBeforeSaved() {
    return Member.builder()
        .membershipId(MEMBERSHIP_ID)
        .memberInfo(MEMBER_INFO)
        .build();
  }

  public static Member createBeforeSaved(MemberInfo memberInfo) {
    return Member.builder()
        .membershipId(MEMBERSHIP_ID)
        .memberInfo(memberInfo)
        .build();
  }

  public static Member create() {
    return Member.builder()
        .id(ID)
        .uuid(MEMBER_UUID)
        .createdAt(CREATED_AT)
        .memberInfo(MEMBER_INFO)
        .membershipId(MEMBERSHIP_ID)
        .build();
  }

  public static Member create(MemberInfo memberInfo) {
    return Member.builder()
        .id(ID)
        .uuid(MEMBER_UUID)
        .createdAt(CREATED_AT)
        .memberInfo(memberInfo)
        .membershipId(MEMBERSHIP_ID)
        .build();
  }
}
