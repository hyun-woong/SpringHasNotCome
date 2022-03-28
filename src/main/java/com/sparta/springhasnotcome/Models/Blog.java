package com.sparta.springhasnotcome.Models;

import com.sparta.springhasnotcome.Dto.BlogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Blog extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String userId;

    public Blog(BlogRequestDto requestDto){
        this.username = getUsername();
        this.title = getTitle();
        this.userId = getUserId();
        this.contents = getContents();
    }

    public void update(BlogRequestDto requestDto){
        this.username = requestDto.getUsername();;
        this.contents = requestDto.getContents();
    }

}
