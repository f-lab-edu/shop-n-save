package com.flab.demo.mapper;

import com.flab.demo.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.flab.demo.fixture.UserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "/clean-all.sql")
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("User 정보를 email, password, name 을 입력받아 저장한다.")
    void save_test() {
        //given
        int addCount = userMapper.save(TEST_USER);

        //then
        assertThat(addCount).isEqualTo(1);
    }

    @Test
    @DisplayName("User 정보를 email 로 검색할 상황일 때 email 에 해당하는 User 를 리턴한다.")
    void findByEmail_test() {
        //given
        userMapper.save(TEST_USER);

        //when
        User findUser = userMapper.findByEmail(TEST_EMAIL);

        //then
        assertThat(findUser.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(findUser.getPassword()).isEqualTo(TEST_PASSWORD);
    }

    @Test
    @DisplayName("로그인 시 email 과 password 로 사용자를 검색할 상황일 때 해당하는 User 를 리턴한다.")
    void findByEmailAndPassword_test() {
        //given
        userMapper.save(USER_WITH_ENCRYPT);

        //when
        User findUser = userMapper.findByEmailAndPassword(LOGIN_USER_REQUEST_DTO);

        //then
        assertThat(findUser.getId()).isNotNull();
        assertThat(findUser.getEmail()).isEqualTo(TEST_EMAIL);
        assertThat(findUser.getPassword()).isEqualTo(TEST_HASH_PASSWORD);
    }

    @Test
    @DisplayName("로그인 시 email 과 password 잘못된 정보인 상황일 때 아무것도 리턴하지 않는다.")
    void findByEmailAndPassword_fail_test() {
        //given
        userMapper.save(TEST_USER);

        //when
        User findUser = userMapper.findByEmailAndPassword(LOGIN_USER_REQUEST_DTO);

        //then
        assertThat(findUser).isNull();
    }
}
