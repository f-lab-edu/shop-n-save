package com.flab.demo.order.mapper;

import com.flab.demo.domain.Member;
import com.flab.demo.order.domain.Order;
import com.sql.MemberSQL;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface OrderMapper {

    @InsertProvider(type = MemberSQL.class, method = "create")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int placeOrder(@Param("member") Order order);

    @SelectProvider(type = MemberSQL.class, method = "getById")
    Optional<Member> getById(@Param("id") String id);

    @SelectProvider(type = MemberSQL.class, method = "getByEmail")
    Optional<Member> getByEmail(@Param("email") String email);

    @SelectProvider(type = MemberSQL.class, method = "modifyById")
    void modifyById(@Param("id") String id, @Param("member") Member member);
}

