package app.controller;

import app.info.FieldInfo;
import app.info.FieldTypeInfo;
import app.info.UserInfo;
import app.model.User;
import app.service.FieldService;
import app.service.FieldTypeService;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public ModelAndView index(@RequestParam(name = "page", required = false) Optional<Integer> page) {

        ModelAndView model = new ModelAndView("views/users/index");
        String title = "Users List";

        UserInfo userInfo = new UserInfo();
        userInfo.setPage(page.orElse(1));

        model.addObject("title", title);
        Page<UserInfo> data = userService.paginate(userInfo);
        model.addObject("data", data);

        return model;
    }

    @GetMapping(path = {"/login", "users/login"})
    public String login(){
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            return "redirect:/";
        }
        return "views/auth/login";
    }

    @GetMapping(path = {"/register", "users/register"})
    public String register(Model model){
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            return "redirect:/";
        }

        UserInfo userInfo = new UserInfo();

        model.addAttribute("userForm", userInfo);

        return "views/auth/register";
    }

    @PostMapping(path = "/users")
    public String post(@ModelAttribute("userForm") @Valid UserInfo userInfo, BindingResult bindingResult, Model model) {

        userInfo.setId(null);

        if (bindingResult.hasErrors()){
            return "views/auth/register";
        }
        else if (userService.create(userInfo))
            return "redirect:/login";

        return "views/auth/register";
    }
}
