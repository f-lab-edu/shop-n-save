package com.flab.demo.fixture;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.dto.LoginUserRequestDto;

public class UserFixture {
    public static final long TEST_ID = 100L;
    public static final String TEST_EMAIL = "sgkim94@github.com";
    public static final String TEST_PASSWORD = "123456";
    public static final String TEST_NAME = "김상구";

    public static final User TEST_USER = new User(TEST_ID, TEST_EMAIL, TEST_PASSWORD, TEST_NAME);

    public static final CreateUserRequestDto CREATE_USER_REQUEST_DTO
            = new CreateUserRequestDto(TEST_EMAIL, TEST_PASSWORD, TEST_NAME);

    public static final LoginUserRequestDto LOGIN_USER_REQUEST_DTO
            = new LoginUserRequestDto(TEST_EMAIL, TEST_PASSWORD);

}
