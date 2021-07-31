package com.flab.shopnsave.enums;

import com.flab.shopnsave.member.exception.UnknownRoleValueException;
import org.apache.ibatis.type.MappedTypes;

public enum Role implements CodeEnum {

    ADMIN(1), SELLER(2), BASIC_MEMBER(3);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    @Override
    public int getCode() {
        return value;
    }

    public static Role valueOf(int value) {
        switch(value) {
            case 1 : return ADMIN;
            case 2 : return SELLER;
            case 3 : return BASIC_MEMBER; // 로그인 한 모든 사용자
            default : throw new UnknownRoleValueException(value);
        }
    }

    @MappedTypes(Role.class)
    public static class TypeHandler extends CodeEnumTypeHandler<Role> {

        public TypeHandler() {
            super(Role.class);
        }
    }
}
