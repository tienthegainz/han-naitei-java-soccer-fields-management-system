package app.controller;

import app.info.FieldInfo;
import app.info.UserInfo;
import app.service.FieldService;
import app.service.FieldTypeService;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserController extends BaseController {

    private static final Logger logger = Logger.getLogger(FieldController.class);

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
}
