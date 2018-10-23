package com.task.controller;

import com.task.domain.User;
import com.task.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user",new User());
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
       if (userFromDb.getPassword().equals(user.getPassword())) {
            return new ModelAndView("task");
       } else {
           return new ModelAndView("secondWarning");
       }
    }

}
