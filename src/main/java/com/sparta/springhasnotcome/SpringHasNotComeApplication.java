package com.sparta.springhasnotcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class SpringHasNotComeApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringHasNotComeApplication.class, args);

    }
    @PostConstruct //배포 후 수정사항이 있어 수정 후 재배포시, 시간 오류로 인한 오름차순 정렬이 깨지기 때문에, 이를 사용함
    public void started(){ TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul")); }


}
