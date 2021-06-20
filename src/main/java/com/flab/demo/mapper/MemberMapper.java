package com.flab.demo.mapper;

import com.flab.demo.domain.Member;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO MEMBER_INFO(email, password, name) VALUES(#{member.email}, #{member.password}, #{member.name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(@Param("member") Member member);

    @Select("SELECT * FROM MEMBER_INFO WHERE id=#{id}")
    Member getById(@Param("id") String id);

    @Select("SELECT * FROM MEMBER_INFO WHERE email=#{email}")
    Member getByEmail(@Param("email") String email);

    @Update("UPDATE MEMBER_INFO SET password = #{member.password}, name = #{member.name} WHERE id=#{id}")
    void modifyById(@Param("id") String id, @Param("member") Member member);
}
