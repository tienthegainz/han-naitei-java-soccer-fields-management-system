package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "views/home/index";
    }

    @GetMapping("/login")
    public String login(){
        return "views/auth/login";
    }

    @GetMapping("/register")
    public String register(){
        return "views/auth/register";
    }

}
