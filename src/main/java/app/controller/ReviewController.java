package app.controller;

import app.info.FieldTypeInfo;
import app.info.ReviewInfo;
import app.model.User;
import app.service.FieldService;
import app.service.ReviewService;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.InvocationTargetException;

@Controller
public class ReviewController extends BaseController {
    private static final Logger logger = Logger.getLogger(ReviewController.class);

    private final ReviewService reviewService;

    private final FieldService fieldService;

    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, FieldService fieldService, UserService userService) {
        this.reviewService = reviewService;
        this.fieldService = fieldService;
        this.userService = userService;
    }

    @PostMapping(path = "/reviews")
    public String post(ReviewInfo reviewInfo, final RedirectAttributes redirectAttributes) throws InvocationTargetException, IllegalAccessException {
        logger.info("POST");

        User currentUser = userService.getCurrentUser();
        if (currentUser == null)
            return handleRedirect(redirectAttributes, "error", "Unauthorized.", "/fields/" + reviewInfo.getField().getId());

        reviewInfo.setId(null);
        reviewInfo.setUser(currentUser);

        if (reviewService.createReview(reviewInfo))
            return handleRedirect(redirectAttributes, "success", "Review created.", "/fields/" + reviewInfo.getField().getId());

        return handleRedirect(redirectAttributes, "error", "Error creating review.", "/fields/" + reviewInfo.getField().getId());
    }

    @GetMapping("/reviews/{id}/delete")
    public String delete(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        ReviewInfo reviewInfo = reviewService.findReview(id);

        if (reviewInfo == null)
            return handleRedirect(redirectAttributes, "error", "Review not found.", "/fields");

        if (reviewService.deleteFieldType(id))
            return handleRedirect(redirectAttributes, "success", "Field type deleted.", "/fields/" + reviewInfo.getField().getId());

        return handleRedirect(redirectAttributes, "error", "Error deleting field type.", "/fields/" + reviewInfo.getField().getId());
    }
}
