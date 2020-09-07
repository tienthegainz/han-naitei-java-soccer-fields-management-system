package app.controller;

import app.info.FieldInfo;
import app.service.FieldService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class FieldController extends BaseController {
    private static final Logger logger = Logger.getLogger(FieldController.class);

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping(path = "/fields")
    public ModelAndView index(@RequestParam(value = "search", required = false) String search,
                              @RequestParam(name = "page", required = false) Optional<Integer> page) {

        ModelAndView model = new ModelAndView("views/fields/index");
        String title = "Field List";

        FieldInfo fieldInfo = new FieldInfo();
        fieldInfo.setPage(page.orElse(1));

        model.addObject("title", title);
        if (search == null){
            Page<FieldInfo> data = fieldService.paginate(fieldInfo);
            model.addObject("data", data);
//            model.addObject("data", fieldService.loadFields());
        }
        else{
            Page<FieldInfo> data = fieldService.searchFields(search, fieldInfo);
            model.addObject("data", data);
        }

        return model;
    }

    @GetMapping(path = "/fields/{id}")
    public String show(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
        String title = "Field Details";

        FieldInfo fieldInfo = fieldService.findField(id);

        if (fieldInfo == null)
            return handleRedirect(redirectAttributes, "error", "Field type not found.", "/fields");

        model.addAttribute("title", title);
        model.addAttribute("data", fieldInfo);

        return "views/fields/show";
    }
}
