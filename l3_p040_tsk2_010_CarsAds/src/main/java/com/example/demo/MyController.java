package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @Autowired
    IUsersService usersService;

    @RequestMapping("/showUsers")
    public String findCities(Model model) {

        List<Users> allUsers = (List<Users>) usersService.findAll();

        model.addAttribute("allUsers", allUsers);
        return "showUsers";
    }

}
