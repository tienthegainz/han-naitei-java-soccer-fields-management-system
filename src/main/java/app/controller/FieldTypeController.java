package app.controller;

import app.model.FieldType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NamedStoredProcedureQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("field-types")
public class FieldTypeController {
    private static final Logger logger = Logger.getLogger(FieldTypeController.class);

    @GetMapping()
    public ModelAndView index() {
        logger.info("Index");
        ModelAndView model = new ModelAndView("views/field-types/index");
        // TO DO
        // GET FIELD LIST, ADD TO MODEL
        Map<String, String> fieldType1 = new HashMap<String, String>();
        Map<String, String> fieldType2 = new HashMap<String, String>();
        String title = "Field Type List";
        fieldType1.put("Name", "Sân dỏm");
        fieldType2.put("Name", "Sân xịn");
        ArrayList<Map<String, String>> data = new ArrayList<>();
        data.add(fieldType1);
        data.add(fieldType2);
        logger.info(data);
        model.addObject("title", title);
        model.addObject("data", data);
        return model;
    }

    @GetMapping("/create")
    public String create(Model model) {
        logger.info("Create");
        // TO DO
        // Bind data to thymeleaf form
        return "views/field-types/create";
    }

    @PostMapping()
    public String post(Model model, @PathVariable Integer id) {
        logger.info("Post");
        // TO DO
        // Create Logic
        // Redirect after create
        return "redirect: /field-types";
    }

    @DeleteMapping()
    public String delete(Model model, @PathVariable Integer id) {
        logger.info("Delete");
        // TO DO
        // Create Logic
        // Redirect after Delete
        return "redirect: /field-types";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable Integer id) {
        logger.info("Edit");
        // TO DO
        // GET FIELD TYPE BY ID, PASS TO MODEL, RENDER TO VIEW
        Map<String, String> data = new HashMap<String, String>();
        String title = "Field Type";
        data.put("Name", "Sân dỏm");
        model.addAttribute("title", title);
        model.addAttribute("data", data);
        return "views/field-types/edit";
    }


    @PatchMapping("/{id}/edit")
    public String patch(Model model, @PathVariable Integer id) {
        logger.info("Patch");
        // TO DO
        // GET FIELD TYPE BY ID, PASS TO MODEL, RENDER TO VIEW
        // Edit the resource
        // Redirect to show page
        return "redirect: /field-types/" + id;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id) {
        // TO DO
        // GET FIELD TYPE BY ID, PASS TO MODEL, RENDER TO VIEW
        logger.info("Show");
        Map<String, String> data = new HashMap<String, String>();
        String title = "Field Type";
        data.put("Name", "Sân xịn");
        model.addAttribute("title", title);
        model.addAttribute("data", data);
        return "views/field-types/show";
    }
}
