package com.sparta.springhasnotcome.Models;


import com.sparta.springhasnotcome.Dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

//    @ManyToOne//다대일 관계 매핑 정보, 연관관계 매핑시 다중성 어노테이션 필수
//    @JoinColumn(nullable = false)//외래 키 매핑 시 사용
//    private User username;

    public Comment(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
    }


}
