package com.flab.demo.dto.member;

import com.flab.demo.domain.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMemberRequestDto {

    @NotBlank(message = "이메일은 빈 값일 수 없습니다")
    @Email(message = "올바른 형식의 이메일 주소어야 합니다")
    private String email;

    @NotBlank(message = "비밀번호는 빈 값일 수 없습니다")
    private String password;

    @NotBlank(message = "이름은 빈 값일 수 없습니다")
    private String name;
    
    public Member toEntity() {
        Member member = Member.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .build();
        return member;
    }
}
