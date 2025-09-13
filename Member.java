package com.sbs.sample.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //DB Table을 만들기 위한 어노테이션
@Table(name= "member")
@Getter
@Setter // Getter, Setter 자동으로 만들어줌
@NoArgsConstructor  // 매개변수가 없는 생성자를 자동으로 추가 -> 코드 양을 대폭 줄여줌
public class Member {
    @Id // Primary Key 역할 수행 (unique, not null)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 생성 전략 -> primary key에 대한 생성전략을 넣음 , IDENTITY를 넣으면 해당하는 생성 전략을 이어서 받음
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100)   // 이름 필드의 길이 제한
    private String name;

    @Column(nullable = false)   // null을 허용하지 않음
    private String pwd;

    @Column(unique = true, length = 150)    // 무결성을 보장하기 위한 제약조건(unique)
    private String email;

    @Column(length = 255)
    private String image;   // 프로필 사진과 같이 사진을 올리면 너무 커짐, 경로를 넣어주기

    private LocalDateTime regDate;  // 회원 가입 시간

    @PrePersist // DB에 insert 되기 전에 실행되는 메서드
    public void prePersist(){
        this.regDate = LocalDateTime.now(); // 내부적으로 자동적으로 이 메서드를 불러 DB에 inset되기 직전에 현재시간을 가져옴
    }
}
