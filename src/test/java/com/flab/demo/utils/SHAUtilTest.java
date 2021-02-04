package com.flab.demo.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.flab.demo.fixture.UserFixture.TEST_PASSWORD;
import static com.flab.demo.utils.SHAUtil.SHA_256;
import static com.flab.demo.utils.SHAUtil.digest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

class SHAUtilTest {

    @Test
    @DisplayName("입력값을 받아 SHA256 로 암호화하여 리턴한다.")
    void digest_test() {
        String encryptPassword = digest(TEST_PASSWORD, SHA_256);

        assertThat(encryptPassword).isNotBlank();
        assertThat(encryptPassword).isNotEqualTo(TEST_PASSWORD);
    }

    @Test
    @DisplayName("알 수없는 알고리즘을 입력받아 시도하는 경우 예외처리가 발생한다.")
    void digest_fail_test() {
        assertThrows(IllegalArgumentException.class,
                () -> digest(TEST_PASSWORD, "unknown")
        );
    }
}
