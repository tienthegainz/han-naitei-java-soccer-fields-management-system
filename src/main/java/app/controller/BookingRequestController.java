package app.controller;

import app.info.BookingRequestInfo;
import app.info.FieldInfo;
import app.model.BookingRequest;
import app.model.User;
import app.service.BookingRequestService;
import app.service.FieldService;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookingRequestController extends BaseController {
    private static final Logger logger = Logger.getLogger(BookingRequestController.class);

    private final BookingRequestService bookingRequestService;
    private final UserService userService;
    private final FieldService fieldService;

    @Autowired
    public BookingRequestController(BookingRequestService bookingRequestService, UserService userService, FieldService fieldService) {
        this.bookingRequestService = bookingRequestService;
        this.userService = userService;
        this.fieldService = fieldService;
    }

    @GetMapping(path = "/booking-requests")
    public ModelAndView index(HttpServletRequest request, final RedirectAttributes redirectAttributes, Authentication authentication) {
        logger.info("Index");
        ModelAndView model = new ModelAndView("views/booking-requests/index");
        String title = "Booking requests List";
        model.addObject("title", title);
        if (request.isUserInRole("ROLE_ADMIN")) {
            logger.info("IS ADMIN");
            model.addObject("approvedList", bookingRequestService.loadBookingRequestsByStatus(BookingRequest.Status.APPROVED));
            model.addObject("pendingList", bookingRequestService.loadBookingRequestsByStatus(BookingRequest.Status.PENDING));
            model.addObject("canceledList", bookingRequestService.loadBookingRequestsByStatus(BookingRequest.Status.CANCELED));
            model.addObject("rejectedList", bookingRequestService.loadBookingRequestsByStatus(BookingRequest.Status.REJECTED));
        } else {
            logger.info("IS USER");
            User user = userService.findByEmail(authentication.getName());
            model.addObject("approvedList", bookingRequestService.loadBookingRequestsOfUserByStatus(BookingRequest.Status.APPROVED, user.getId()));
            model.addObject("pendingList", bookingRequestService.loadBookingRequestsOfUserByStatus(BookingRequest.Status.PENDING, user.getId()));
            model.addObject("canceledList", bookingRequestService.loadBookingRequestsOfUserByStatus(BookingRequest.Status.CANCELED, user.getId()));
            model.addObject("rejectedList", bookingRequestService.loadBookingRequestsOfUserByStatus(BookingRequest.Status.REJECTED, user.getId()));
        }
        return model;
    }

    @GetMapping(path = "/fields/{id}/booking-requests/new")
    public String createBookingView(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
        logger.info("Create Booking");
        FieldInfo fieldInfo = fieldService.findField(id);
        if (fieldInfo == null)
            return handleRedirect(redirectAttributes, "error", "Field not found.", "/fields");


        BookingRequestInfo bookingRequestInfo = new BookingRequestInfo();
        bookingRequestInfo.setField(fieldInfo.toField());

        String title = "Create Booking Request for Field: " + fieldInfo.getName();
        model.addAttribute("title", title);
        model.addAttribute("createBookingForm", bookingRequestInfo);

        return "views/booking-requests/create";
    }

    @PostMapping(path = "/booking-requests")
    public String create(BookingRequestInfo bookingRequestInfo, final RedirectAttributes redirectAttributes, Authentication authentication) {
        logger.info("POST BOOKING REQUESTS");
        User user = userService.findByEmail(authentication.getName());
        if (user == null) {
            return handleRedirect(redirectAttributes, "error", "User not logged in", "login");
        }
        List<BookingRequestInfo> existedBookingRequest = bookingRequestService.findByPeriod(bookingRequestInfo);

        if (existedBookingRequest.size() != 0) {
            logger.info(existedBookingRequest.size());
            return handleRedirect(redirectAttributes, "error", "Invalid booking request", "fields/" + bookingRequestInfo.getField().getId() + "/create-booking");
        }
        try {
            bookingRequestInfo.setUser(user);
            bookingRequestInfo.setStatus(BookingRequest.Status.PENDING);
            bookingRequestService.createBookingRequest(bookingRequestInfo);
            return handleRedirect(redirectAttributes, "success", "Booking request created.", "/booking-requests");
        } catch (Exception e) {
            return handleRedirect(redirectAttributes, "error", "Invalid booking request", "fields/" + bookingRequestInfo.getField().getId() + "/create-booking");
        }
    }

    @GetMapping("/booking-requests/{id}/delete")
    public String delete(@PathVariable("id") int id, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
        logger.info("DELETE");
        BookingRequestInfo bookingRequestInfo = bookingRequestService.findBookingRequest(id);

        if (bookingRequestInfo == null)
            return handleRedirect(redirectAttributes, "error", "Booking request not found.", "/booking-requests");

        boolean res = false;
        if (request.isUserInRole("ROLE_ADMIN")) {
            res = bookingRequestService.updateBookingRequestStatus(id, BookingRequest.Status.REJECTED);
        } else if (request.isUserInRole("ROLE_USER")) {
            res = bookingRequestService.updateBookingRequestStatus(id, BookingRequest.Status.CANCELED);
        }

        if (res)
            return handleRedirect(redirectAttributes, "success", "Booking request canceled.", "/booking-requests");

        return handleRedirect(redirectAttributes, "error", "Error deleting booking request.", "/booking-requests");
    }

    @PostMapping(path = "/booking-requests/{id}/approve")
    @ResponseBody
    public String approve(@PathVariable("id") int id) {
        logger.info("APPROVE BOOKING REQUESTS");
        logger.info("ID:" + id);
        boolean updatedBookingRequest = bookingRequestService.updateBookingRequestStatus(id, BookingRequest.Status.APPROVED);
        if (!updatedBookingRequest) {
            return "failed";
        }
        return "success";
    }
}
