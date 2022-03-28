package com.sparta.springhasnotcome.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //자동으로 객체를 생성해줌
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;

}
