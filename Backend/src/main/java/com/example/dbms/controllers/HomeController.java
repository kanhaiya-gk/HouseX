package com.example.dbms.controllers;

// import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Development

@RestController
@CrossOrigin
public class HomeController {
    @GetMapping("/home")
    public void method(@RequestParam String token, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "http://localhost:3000/home?token=" + token);
        httpServletResponse.setStatus(302);
    }
}

// HttpServletRequest httpServletRequest

