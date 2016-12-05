package com.slipp.web;

import com.slipp.model.User;
import com.slipp.model.UserRepository;
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

    @GetMapping("/{id}")
    public  String list(@PathVariable Long id, Model model, User user, HttpSession session){
        Object tempUser = session.getAttribute("loginUser");
        if(tempUser == null)
            return "redirect:/users/login";

        User loginUser = (User) tempUser;
        if(!loginUser.matchId((id)))
            throw new IllegalStateException("다른사람의 정보를 조회할 수 없습니다");

        model.addAttribute("users", userRepository.findOne(id));
        return "/user/list";
    }

    @GetMapping("/form")
    public String form(){
        return "/user/form";
    }

    @GetMapping("{id}/form")
    public String updateForm(@PathVariable Long id,  Model model, HttpSession session){
        if(!HttpSessionUtils.isLoginUser(session))
            return "redirect:/users/login";

        User sessionedUser = HttpSessionUtils.getUserFromSession(session);
        if(!sessionedUser.matchId(id))
            throw new IllegalStateException("You can only edit your data");

        model.addAttribute("user", sessionedUser);
        return "/user/updateForm";
    }

    @PostMapping("{id}/edit")
    public String update(@PathVariable Long id, User user, HttpSession session){
        if(!HttpSessionUtils.isLoginUser(session))
            return "redirect:/users/login";

        User sessionedUser = HttpSessionUtils.getUserFromSession(session);
        if(!sessionedUser.matchId(id))
            throw new IllegalStateException("You can only edit your data");

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

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session){
        User dbUser = userRepository.findByUserId(userId);
        if(dbUser == null)
            return "redirect:/users/login";

        if(!dbUser.matchPassword(password)){
            return"redirect:/users/login";
        }

        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, dbUser);

        return "redirect:/questions";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/user/login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/questions";
    }

}
