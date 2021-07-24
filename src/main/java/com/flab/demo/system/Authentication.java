package com.flab.demo.system;

import com.flab.demo.member.domain.AuthMember;
import com.flab.demo.member.dto.LoginMemberRequestDto;

public interface Authentication {

    public void login(LoginMemberRequestDto loginMemberRequestDto);

    public AuthMember getLoginMember();

    public void logout();
}
