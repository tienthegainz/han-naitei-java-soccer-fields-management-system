package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "views/auth/login";
    }

    @GetMapping("/register")
    public String register(){
        return "views/auth/register";
    }

}
