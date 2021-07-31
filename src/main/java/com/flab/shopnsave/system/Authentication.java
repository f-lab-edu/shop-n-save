package com.flab.shopnsave.system;

import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.member.dto.LoginMemberRequestDto;

public interface Authentication {

    public void login(LoginMemberRequestDto loginMemberRequestDto);

    public AuthMember getLoginMember();

    public void logout();
}
