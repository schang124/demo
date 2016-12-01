package com.example.web;

import com.example.model.User;
import com.example.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by schang124 on 2016/11/24.
 */

@Controller
@RequestMapping("/users")
public class UserController {
    private ArrayList<User> users = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public  String list(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }

    @GetMapping("/form")
    public String form(){
        return "/user/form";
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Long id,  Model model){
        model.addAttribute("user", userRepository.findOne(id));
        return "/user/updateForm";
    }

    @PostMapping("{id}/edit")
    public String update(@PathVariable Long id, User user){
        User dbUser = userRepository.findOne(id);
        try{
            dbUser.update(user);
        } catch(IllegalArgumentException e){
            e.printStackTrace();

        }

        userRepository.save(dbUser);

        return "redirect:/users";
    }

    @PostMapping("/create")
    public String create(User user) {

//        System.out.println("=====>user: " + user);
//        users.add(user);
        userRepository.save(user);

        return "redirect:/users";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/user/login";
    }
}
