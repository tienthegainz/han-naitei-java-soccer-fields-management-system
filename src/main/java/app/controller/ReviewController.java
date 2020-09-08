package app.controller;

import app.service.FieldService;
import app.service.ReviewService;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
