package com.sparta.springhasnotcome.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PostRequestDto {

    private String username;
    private String title;
    private String contents;
    private Long id;
}
