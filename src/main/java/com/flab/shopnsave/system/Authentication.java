package com.flab.shopnsave.system;

import com.flab.shopnsave.member.domain.AuthMember;
import com.flab.shopnsave.member.dto.LoginMemberRequestDto;

import java.util.Optional;

public interface Authentication {

    public void login(LoginMemberRequestDto loginMemberRequestDto);

    public Optional<AuthMember> getLoginMember();

    public void logout();
}
