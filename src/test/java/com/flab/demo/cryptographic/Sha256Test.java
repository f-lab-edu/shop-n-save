package com.flab.demo.cryptographic;

import com.flab.demo.security.Cryptographic;
import com.flab.demo.security.Sha256;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static com.flab.demo.fixture.UserFixture.TEST_PASSWORD;
import static com.flab.demo.security.Sha256.SHA_256;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class Sha256Test {

    private Cryptographic cryptographic;

    @Before
    public void setUp() {
        cryptographic = new Sha256();
    }

    @Test
    @DisplayName("입력값을 받아 SHA256 로 암호화하여 리턴한다.")
    public void digest_test() {
        String encryptPassword = cryptographic.digest(TEST_PASSWORD, SHA_256);

        assertThat(encryptPassword).isNotBlank();
        assertThat(encryptPassword).isNotEqualTo(TEST_PASSWORD);
    }

    @Test
    @DisplayName("알 수없는 알고리즘을 입력받아 시도하는 경우 예외처리가 발생한다.")
    public void digest_fail_test() {
        assertThrows(IllegalArgumentException.class,
                () -> cryptographic.digest(TEST_PASSWORD, "unknown")
        );
    }
}
