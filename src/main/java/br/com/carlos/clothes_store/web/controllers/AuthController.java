package br.com.carlos.clothes_store.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/admin")
public class AuthController {

    @GetMapping("/login")
    public String login() {

        return "admin/auth/login";
    }  
}
