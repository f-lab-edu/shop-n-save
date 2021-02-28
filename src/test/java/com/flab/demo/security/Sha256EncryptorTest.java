package com.flab.demo.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.flab.demo.fixture.UserFixture.TEST_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

class Sha256EncryptorTest {
    private Encryptor Sha256Encryptor;

    @BeforeEach
    void setUp() {
        Sha256Encryptor = new Sha256Encryptor();
    }

    @Test
    void encryptPassword_test() {
        //when
        String encryptPassword = Sha256Encryptor.encrypt(TEST_PASSWORD, new Sha256());

        //then
        assertThat(encryptPassword).isNotEqualTo(TEST_PASSWORD);
    }
}
