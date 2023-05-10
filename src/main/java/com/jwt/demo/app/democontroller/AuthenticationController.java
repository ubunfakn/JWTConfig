package com.jwt.demo.app.democontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jwt.demo.app.demoapp.auth.*;

import lombok.RequiredArgsConstructor;


//This is the main controller this will handle two requests from the client if the client is
// having token then it will authenticate if not then can register the client

@RestController
@RequestMapping("/user/api")
@RequiredArgsConstructor // From lomnok Developer tools dependency just by adding this annotation 
                // You don't need to create a class constructor it will automatically generated
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService; //Custom Class
    
    @PostMapping("/register")// This handler is for registering the client if not already exists
    public ResponseEntity <AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        //Here AuthenticationResponse and RegisterRequest are the custom classes

        //This handler will accept register request in JSON form and do register the client 

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity <AuthenticationResponse> register(@RequestBody AuthenticationRequest request)
    {
        //Here AuthenticationResponse and AuthenticationRequest are the custom classes

        // This will accept authentication request means username and password in JSON form
        //After accepting it will authenticate the credentials and if it is correct then allow
        // the client to access the URL else throw a 403 forbidden unauthorized access

        
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
