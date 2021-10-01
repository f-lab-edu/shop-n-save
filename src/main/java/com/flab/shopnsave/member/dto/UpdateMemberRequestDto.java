package com.flab.shopnsave.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMemberRequestDto {

    private String password;
    private String name;
    private String address;
    private String phone;

    public Optional<String> getUpdatablePassword() {
        return Optional.ofNullable(password).filter(this::isNotBlank);
    }

    public Optional<String> getUpdatableName() {
        return Optional.ofNullable(name).filter(this::isNotBlank);
    }

    public Optional<String> getUpdatableAddress() {
        return Optional.ofNullable(address).filter(this::isNotBlank);
    }

    public Optional<String> getUpdatablePhone() {
        return Optional.ofNullable(phone).filter(this::isNotBlank);
    }

    private boolean isNotBlank(String string) {
        return !StringUtils.isBlank(string);
    }
}