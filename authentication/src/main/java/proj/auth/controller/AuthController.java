package proj.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/")
@Controller
public class AuthController {
    @GetMapping
    @RequestMapping("/validate")
    public ResponseEntity validate(@RequestParam String param) {
        System.out.println("validation");
        return param.equals("OK") ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    @RequestMapping("/register")
    public String register() {
        System.out.println("registration");
        return "auth";
    }
}
