package com.example.demo.dto;

import com.example.demo.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
public class articleform {

    private String title;
    private String content;
    private Long id;

    public Article toEntity() {
        return new Article(id, title, content);

    }
}
