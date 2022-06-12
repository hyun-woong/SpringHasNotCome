package com.sparta.springhasnotcome.Models;

import com.sparta.springhasnotcome.Dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Blog extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

//    @Column
//    private Long userId;
//
//    @ManyToOne
//    @JoinColumn(nullable=false)
//    private User user;
//
//    public Blog(Long userId ,String username, String title, String contents){
//        this.username = username;
//        this.title = title;
//        this.contents = contents;
//        this.userId = userId;
//    }


    public Blog(PostRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
