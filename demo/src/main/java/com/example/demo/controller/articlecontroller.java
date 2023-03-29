package com.example.demo.controller;

import com.example.demo.dto.articleform;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller // 뷰페이지를 반환
@Slf4j
public class articlecontroller {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/new")
    public String newarticleForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createarticle(articleform form){
        log.info(form.toString());

        //dto를 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());

        // repository에게 entity를 db안에 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //       System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // id를 데이터로 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 가져온 데이터를 model에 등록
        model.addAttribute("article", articleEntity);
        // 보여줄 페이지
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){

        // 모든 article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();
        // 가져온 article 묶음을 뷰로 전달
        model.addAttribute("articlelist", articleEntityList);
        // 뷰 페이지 설정
        return "/articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 등록
        model.addAttribute("article", articleEntity);
        //뷰페이지 설정
        return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(articleform form){
        log.info(form.toString());

        // Dto를 엔티티로
        Article articleEntity = form.toEntity();
        //엔티티를 db에 저장
        //db에 기존 데이터를 가져온다
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //기존 데이터가 있다면 갱신
        if(target != null){
            articleRepository.save(articleEntity);
        }
        //수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article target = articleRepository.findById(id).orElse(null);
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "delect complete");
        }
        return "redirect:/articles/";
    }
}
