package app.controller;

import app.service.FieldService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FieldController {
    private static final Logger logger = Logger.getLogger(FieldController.class);

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping(path = "/fields")
    public ModelAndView index(@RequestParam(value = "search", required = false) String search) {
        logger.info("Index");

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

}
