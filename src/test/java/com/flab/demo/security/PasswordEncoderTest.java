package com.flab.demo.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.flab.demo.fixture.UserFixture.TEST_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

class PasswordEncoderTest {
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new PasswordEncoder();
    }

    @Test
    void encryptPassword_test() {
        //when
        String encryptPassword = encryptPasswordBySha256();

        //then
        assertThat(encryptPassword).isNotEqualTo(TEST_PASSWORD);
    }

    @Test
    void encryptPasswordBySha_test() {
        //when
        String encryptPassword = passwordEncoder.encryptPasswordBySha256(TEST_PASSWORD);
        String secondEncryptPassword = encryptPasswordBySha256();

        //then
        assertThat(encryptPassword).isEqualTo(secondEncryptPassword);
    }

    private String encryptPasswordBySha256() {
        return passwordEncoder.encryptPassword(TEST_PASSWORD, new Sha256());
    }
}
