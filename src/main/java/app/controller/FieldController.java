package app.controller;

import app.info.FieldInfo;
import app.info.ReviewInfo;
import app.service.FieldService;
import app.service.FieldTypeService;
import app.service.ReviewService;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Optional;

@Controller
public class FieldController extends BaseController {

    private final FieldService fieldService;

    private final FieldTypeService fieldTypeService;

    private final ReviewService reviewService;

    private final UserService userService;

    @Autowired
    public FieldController(FieldService fieldService, FieldTypeService fieldTypeService, ReviewService reviewService, UserService userService) {
        this.fieldService = fieldService;
        this.fieldTypeService = fieldTypeService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping(path = {"/fields", "/"})
    public ModelAndView index(@RequestParam(value = "search", required = false) String search,
                              @RequestParam(name = "page", required = false) Optional<Integer> page) {

        ModelAndView model = new ModelAndView("views/fields/index");
        String title = "Field List";

        FieldInfo fieldInfo = new FieldInfo();
        fieldInfo.setPage(page.orElse(1));

        model.addObject("title", title);

        Page<FieldInfo> data;
        if (search == null) {
            data = fieldService.paginate(fieldInfo);
            model.addObject("data", data);
//            model.addObject("data", fieldService.loadFields());
        } else {
            data = fieldService.searchFields(search, fieldInfo);
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

        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setField(fieldInfo.toField());

        String avgRating = reviewService.averageRatingByFieldId(id);
        Long totalReview = reviewService.sumReviewByFieldId(id);
        HashMap<Integer, Long> countRating = reviewService.countReviewGroupByRating(id);

        model.addAttribute("title", title);
        model.addAttribute("data", fieldInfo);
        model.addAttribute("currentUser", userService.getCurrentUser());
        model.addAttribute("currentUsersReview", reviewService.loadCurrentUsersReview(fieldInfo));
        model.addAttribute("otherUsersReviews", reviewService.loadOtherUsersReviews(fieldInfo));
        model.addAttribute("avgRating", avgRating);
        model.addAttribute("totalReview", totalReview);
        model.addAttribute("reviewForm", reviewInfo);
        model.addAttribute("countRating", countRating);

        return "views/fields/show";
    }

    @GetMapping(path = "/fields/create")
    public String create(Model model) {
        String title = "Create New Field Type";

        FieldInfo fieldInfo = new FieldInfo();

        model.addAttribute("title", title);
        model.addAttribute("fieldForm", fieldInfo);
        model.addAttribute("fieldTypes", fieldTypeService.loadFieldTypes());


        return "views/fields/create";
    }

    @PostMapping(path = "/fields")
    public String post(FieldInfo fieldInfo, final RedirectAttributes redirectAttributes) {

        fieldInfo.setId(null);

        if (fieldService.createField(fieldInfo))
            return handleRedirect(redirectAttributes, "success", "Field type created.", "/fields");

        return handleRedirect(redirectAttributes, "error", "Error creating field type.", "/fields");
    }
}
