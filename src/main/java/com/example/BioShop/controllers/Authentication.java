package com.example.BioShop.controllers;

import com.example.BioShop.Security.MyUserDetailsService;
import com.example.BioShop.entities.AuthenticationRequest;
import com.example.BioShop.entities.AuthenticationResponse;
import com.example.BioShop.entities.MyUser;
import com.example.BioShop.utils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class Authentication {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JWT jwtToken;

    @PostMapping("/Authentication")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
       try{
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
           );
       }
       catch (DisabledException e){
           throw new Exception("User Disabled", e);
       }
       catch (BadCredentialsException e){
           throw new Exception("Incorrect username or password", e);
       }
       final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
       final  String token = jwtToken.generateToken(userDetails);
       return ResponseEntity.ok(new AuthenticationResponse(token));

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody MyUser user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }
}
