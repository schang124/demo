package com.example.web;

import com.example.model.Question;
import com.example.model.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by schang124 on 2016/11/24.
 */
@Controller
public class QuestionController {

    ArrayList<Question> questions = new ArrayList<>();

    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping("qnas/form")
    public String form(){
        return "/qna/form";
    }

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("questions", questionRepository.findAll());
        return "index";
    }

    @PostMapping("qnas/create")
    public String create(Question question){
        System.out.println("====>question: " + question.toString());
        questionRepository.save(question);
        return "redirect:/";
    }
}
