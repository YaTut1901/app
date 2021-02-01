package proj.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proj.auth.model.User;
import proj.auth.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@RequestMapping("/auth")
@Controller
public class AuthController {
    private final UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String register(HttpServletRequest request) {
        System.out.println("registration");
        return "auth";
    }

    @PostMapping
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("user saving");
        String email = request.getParameter("email");
        String psw = request.getParameter("psw");
        service.save(new User(email, psw));
        Cookie cookie = new Cookie("AUTHORIZED", "true");
        cookie.setMaxAge(60);
        response.addCookie(new Cookie("AUTHORIZED", "true"));
    }
}
