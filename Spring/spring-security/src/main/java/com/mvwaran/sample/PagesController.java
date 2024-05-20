package com.mvwaran.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PagesController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/dashboard")
    public String dashboardPost() {
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/dashboard/admin")
    public String dashboardAdmin() {
        return "dashboard_admin";
    }

    @GetMapping("/dashboard/guest")
    public String dashboardGuest() {
        return "dashboard_guest";
    }
}
