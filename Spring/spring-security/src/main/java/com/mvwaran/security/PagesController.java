package com.mvwaran.security;

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

    @GetMapping("/dashboard/hr")
    public String dashboardHr() {
        return "dashboard_hr";
    }

    @GetMapping("/dashboard/manager")
    public String dashboardManager() {
        return "dashboard_manager";
    }

    @GetMapping("/dashboard/developer")
    public String dashboardDeveloper() {
        return "dashboard_developer";
    }
}
