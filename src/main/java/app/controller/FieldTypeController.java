package app.controller;

import app.info.FieldTypeInfo;
import app.service.FieldTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FieldTypeController extends BaseController {

    private final FieldTypeService fieldTypeService;

    @Autowired
    public FieldTypeController(FieldTypeService fieldTypeService) {
        this.fieldTypeService = fieldTypeService;
    }

    @GetMapping(path = "/field-types")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("views/field-types/index");
        String title = "Field Types";

        model.addObject("title", title);
        model.addObject("data", fieldTypeService.loadFieldTypes());
        return model;
    }

    @GetMapping(path = "/field-types/{id}")
    public String show(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {

        FieldTypeInfo fieldTypeInfo = fieldTypeService.findFieldType(id);
        if (fieldTypeInfo == null)
            return handleRedirect(redirectAttributes, "error", "Field type not found.", "/field-types");

        String title = fieldTypeInfo.getName();

        model.addAttribute("title", title);
        model.addAttribute("data", fieldTypeInfo);

        return "views/field-types/show";
    }

    @GetMapping(path = "/field-types/create")
    public String create(Model model) {
        String title = "Create New Field Type";

        FieldTypeInfo fieldTypeInfo = new FieldTypeInfo();

        model.addAttribute("title", title);
        model.addAttribute("fieldTypeForm", fieldTypeInfo);

        return "views/field-types/create";
    }

    @GetMapping(path = "/field-types/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {

        FieldTypeInfo fieldTypeInfo = fieldTypeService.findFieldType(id);

        if (fieldTypeInfo == null)
            return handleRedirect(redirectAttributes, "error", "Field type not found.", "/field-types");

        String title = fieldTypeInfo.getName() + " | Edit";

        model.addAttribute("title", title);
        model.addAttribute("fieldTypeForm", fieldTypeInfo);

        return "views/field-types/edit";
    }

    @GetMapping("/field-types/{id}/delete")
    public String delete(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        FieldTypeInfo fieldTypeInfo = fieldTypeService.findFieldType(id);

        if (fieldTypeInfo == null)
            return handleRedirect(redirectAttributes, "error", "Field type not found.", "/field-types");

        if (fieldTypeService.deleteFieldType(id))
            return handleRedirect(redirectAttributes, "success", "Field type deleted.", "/field-types");

        return handleRedirect(redirectAttributes, "error", "Error deleting field type.", "/field-types");
    }

    @PostMapping(path = "/field-types")
    public String post(FieldTypeInfo fieldTypeInfo, final RedirectAttributes redirectAttributes) {

        fieldTypeInfo.setId(null);

        if (fieldTypeService.createFieldType(fieldTypeInfo))
            return handleRedirect(redirectAttributes, "success", "Field type created.", "/field-types");

        return handleRedirect(redirectAttributes, "error", "Error creating field type.", "/field-types");
    }

    @PutMapping(path = "/field-types/{id}")
    public String put(@PathVariable("id") int id, FieldTypeInfo fieldTypeInfo, final RedirectAttributes redirectAttributes) {

        if (fieldTypeService.updateFieldType(fieldTypeInfo))
            return handleRedirect(redirectAttributes, "success", "Field type details updated.", "/field-types/" + id);

        return handleRedirect(redirectAttributes, "error", "Error updating field type details.", "/field-types/" + id);
    }

}
