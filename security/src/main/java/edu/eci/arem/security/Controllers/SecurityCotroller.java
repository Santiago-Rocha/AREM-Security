package edu.eci.arem.security.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/nt-ms")
public class SecurityCotroller {
    @RequestMapping(value="/data", method = RequestMethod.GET)
    public String getData(@RequestParam String num){
        int number =  Integer.parseInt(num);
        return Integer.toString(number*number);
    }
}
