package com.example.web;

import com.example.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

/**
 * Created by schang124 on 2016/11/24.
 */
@Controller
public class QuestionController {

    ArrayList<Question> questions = new ArrayList<>();

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("questions", questions);
        return "index";
    }

    @PostMapping("/qna/create")
    public String create(Question question){
        System.out.println("====>question: " + question.toString());
        questions.add(question);
        return "redirect:/";
    }
}
