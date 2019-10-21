package edu.eci.arem.securityclient.Controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SecurityCotroller {

    @Autowired
    RestTemplate restTemplate;

    @Value("${endpoint.ms-serice}")
	private String msEndpoint;

    @RequestMapping(value="/data", method = RequestMethod.POST)
    public String getData(){
        System.out.println("Cargando");
        return "Hola desde el cliente";
    }

    

    @RequestMapping(value="/ms-data", method = RequestMethod.POST)
    public String getMsData(@RequestBody String num){
        System.out.println("Cargando");
        try{
            num = num.replace('=', ' ').trim();
           return restTemplate.getForObject(new URI(msEndpoint+"?num="+num), String.class);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return "Algo salió mal";
    }


}