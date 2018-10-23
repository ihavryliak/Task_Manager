package com.task.controller;

import com.task.domain.Role;
import com.task.domain.User;
import com.task.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String welcome() {
        return "start";
    }

    @GetMapping("/registration")
    public ModelAndView registration(Model model) { ;
        return new ModelAndView("registration","user",new User());}


    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user,
                                Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return "warning";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }

}
