package com.slipp.web;

import com.slipp.model.AnswerRepository;
import com.slipp.model.Question;
import com.slipp.model.QuestionRepository;
import com.slipp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by schang124 on 2016/11/24.
 */
@Controller
@RequestMapping("/questions")
public class QuestionController {

    ArrayList<Question> questions = new ArrayList<>();

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("questions", questionRepository.findAll());
        return "index";
    }

    @RequestMapping("/form")
    public String form(HttpSession session){
        if(!HttpSessionUtils.isLoginUser(session))
            return "redirect:/users/login";

        return "/question/form";
    }

    @PostMapping("/create")
    public String create(String title, String contents, HttpSession session){
        if(!HttpSessionUtils.isLoginUser(session))
            return "redirect:/users/login";

        User sessionedUser = HttpSessionUtils.getUserFromSession(session);
        Question newQuestion = new Question(sessionedUser, title, contents);
        questionRepository.save(newQuestion);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model){
        model.addAttribute("question", questionRepository.findOne(id));
        return "/question/show";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        return "redirect:/";
    }
}
