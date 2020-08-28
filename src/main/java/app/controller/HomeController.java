package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/detail-layout")
    public String detailLayout(Model model){
        Map<String, String> info = new HashMap<String, String>();
        String title = "Field";
        String image = "https://cdn1.sportngin.com/attachments/photo/1040/6534/Soccer_Field_large.jpg";
        info.put("Name", "Handio Field");
        info.put("Area", "12");
        info.put("Address", "Handico, Pham Hung");
        info.put("Price", "$25");
        model.addAttribute("title", title);
        model.addAttribute("allInfo", info);
        model.addAttribute("image", image);
        return "views/layout/detail-layout";
    }
}
