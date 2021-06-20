package com.flab.demo.system;

import com.flab.demo.dto.CreateMemberRequestDto;

public interface Authentification {

    public void login(CreateMemberRequestDto createMemberRequestDto);

    public void logout();
}
