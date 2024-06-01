package com.DevTino.play_tino.user.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MainController {

    @GetMapping("/")
    public String healthCheck() {
        return "server on";
    }
}
