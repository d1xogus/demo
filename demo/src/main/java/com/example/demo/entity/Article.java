package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor // 디폴트 생성자
@Entity // DB가 해당 객체를 인식 (해당 클래스로 테이블을 만듬)
public class Article {

    @Id // 대표값
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID를 자동 생성 어노테이션
    @Column
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

}
