package com.sparta.springhasnotcome.Models;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@MappedSuperclass //Entity가 자동으로 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class) // 생성/변경 시간을 자동으로 업데이트

public abstract class Timestamped {
    //abstract = 추상을 의미, new Timestamped 같은 형식으로 새로이 글자를 쓸 수 없음
    //항상 상속되어서 사용되어야 함
    //시간을 임의로 변경하면 안되기 때문일거라 사료됨

    @CreatedDate //생성시간
    private LocalDate createdAt;

    @LastModifiedDate //수정시간
    private LocalDate modifiedAt;

}
