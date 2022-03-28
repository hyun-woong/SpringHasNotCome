package com.sparta.springhasnotcome.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{3,10}", message = "아이디는 3~10자 영문 대 소문자, 숫자를 사용하세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{4,16}", message = "비밀번호는 4~16자 영문 대 소문자, 숫자를 사용하세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String passwordCheck;

    @Email
    @NotBlank(message = "이메일을 입력해주세요을")
    private String email;

    private boolean admin = false;
    private String adminToken = "";
}