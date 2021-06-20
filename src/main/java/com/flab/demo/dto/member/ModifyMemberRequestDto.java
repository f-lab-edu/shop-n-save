package com.flab.demo.dto.member;

import com.flab.demo.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModifyMemberRequestDto {

    @NotBlank(message = "비밀번호는 빈 값일 수 없습니다")
    private String password;

    @NotBlank(message = "이름은 빈 값일 수 없습니다")
    private String name;

    public Member toEntity() {
        Member member = Member.builder()
                .password(this.password)
                .name(this.name)
                .build();
        return member;
    }
}