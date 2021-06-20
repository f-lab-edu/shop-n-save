package com.flab.demo.system;

import com.flab.demo.dto.member.LoginMemberRequestDto;

public interface Authentification {

    public void login(LoginMemberRequestDto loginMemberRequestDto);

    public String getLoginMemberEmail();
}
