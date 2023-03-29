package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class maincontroller {

    @GetMapping("/hi")
    public String nicetomeetyou(Model model){
        model.addAttribute("username","xogus");
        return "greeting";
    }
    @GetMapping("/bye")
    public String seeyouNext(Model model) {
        model.addAttribute("nickname", "dlxo");
        return "goodbye";
    }
}
