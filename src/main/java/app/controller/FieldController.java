package app.controller;

import app.model.Field;
import app.service.FieldService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FieldController extends BaseController {
    private static final Logger logger = Logger.getLogger(FieldController.class);

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping(path = "/fields")
    public ModelAndView index(@RequestParam(value = "search", required = false) String search) {

        ModelAndView model = new ModelAndView("views/fields/index");
        String title = "Field List";

        model.addObject("title", title);
        if (search == null){
            model.addObject("data", fieldService.loadFields());
        }
        else{
            model.addObject("data", fieldService.searchFields(search));
        }

        return model;
    }

    @GetMapping(path = "/fields/{id}")
    public String show(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
        String title = "Field Details";

        Field field = fieldService.findField(id);

        if (field == null)
            return handleRedirect(redirectAttributes, "error", "Field type not found.", "/fields");

        model.addAttribute("title", title);
        model.addAttribute("data", field);

        return "views/fields/show";
    }
}
