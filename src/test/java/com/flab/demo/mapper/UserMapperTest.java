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
}
