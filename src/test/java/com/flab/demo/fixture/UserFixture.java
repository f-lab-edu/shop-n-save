package com.flab.demo.fixture;

import com.flab.demo.domain.User;
import com.flab.demo.dto.CreateUserRequestDto;
import com.flab.demo.hash.Sha256;

public class UserFixture {
    public static final String TEST_EMAIL = "sgkim94@github.com";
    public static final String TEST_PASSWORD = "123456";
    public static final String TEST_HASH_PASSWORD = new Sha256().digest(TEST_PASSWORD, Sha256.SHA_256);
    public static final String TEST_NAME = "김상구";

    public static final User TEST_USER = User.builder()
            .email(TEST_EMAIL)
            .password(TEST_PASSWORD)
            .name(TEST_NAME)
            .build();

    public static final User TEST_USER_WITH_HASH_PASSWORD = User.builder()
            .email(TEST_EMAIL)
            .password(TEST_HASH_PASSWORD)
            .name(TEST_NAME)
            .build();


    public static final CreateUserRequestDto CREATE_USER_REQUEST_DTO
            = new CreateUserRequestDto(TEST_EMAIL, TEST_NAME, TEST_PASSWORD);
}
