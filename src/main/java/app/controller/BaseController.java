package app.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class BaseController {

    protected String handleRedirect(final RedirectAttributes redirectAttributes, String status, String message, String redirectEndpoint) {
        redirectAttributes.addFlashAttribute("status", status);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:" + redirectEndpoint;
    }
}
