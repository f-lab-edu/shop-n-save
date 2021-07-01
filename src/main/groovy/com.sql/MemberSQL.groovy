package com.sql

import com.flab.demo.domain.Member
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.jdbc.SQL

class MemberSQL {

    public String create(@Param("member") Member member) {
        return new SQL() {{
            INSERT_INTO("MEMBER_INFO")
            VALUES("email, password, name", "#{member.email}, #{member.password}, #{member.name}")
        }}
    }

    public String getById(@Param("id") String id) {
        return new SQL() {{
            SELECT("*")
            FROM("MEMBER_INFO")
            WHERE("id=#{id}")
        }}
    }

    public String getByEmail(@Param("email") String email) {
        return new SQL() {{
            SELECT("*")
            FROM("MEMBER_INFO")
            WHERE("email=#{email}")
        }}
    }

    public String modifyById(@Param("id") String id, @Param("member") Member member) {
        return new SQL() {{
            UPDATE("MEMBER_INFO")
            SET("password=#{member.password}")
            SET("name=#{member.name}")
            WHERE("id=#{id}")
        }}
    }
}