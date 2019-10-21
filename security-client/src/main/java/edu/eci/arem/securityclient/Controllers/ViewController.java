package edu.eci.arem.securityclient.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping({"/", "/index","/login"})
    public String login(){
        return "login";
    }

    @GetMapping("/calc")
    public String cal(){
        return "calc";
    }


}