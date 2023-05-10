package com.jwt.demo.app.democontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//This controller is just for testing the GET reuqest and 
//demonstrtaing that this URL "/user/demo-controller" is
//unauthorized to access the API

@RestController
@RequestMapping("/user/demo-controller")
public class Controller {
    
    @GetMapping
    public ResponseEntity<String> sayHello()
    {
        return ResponseEntity.ok("Hello from tired ankit");
    }
}
