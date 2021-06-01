package com.flab.demo.system;

import com.flab.demo.exception.member.UnknownRoleValueException;

public enum Role {

    ADMIN(1), SELLER(2), BASIC_MEMBER(3);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    public static Role valueOf(int value) {
        switch(value) {
            case 1 : return BASIC_MEMBER;
            case 2 : return SELLER;
            case 3 : return ADMIN;
            default : throw new UnknownRoleValueException(value);
        }
    }
}
