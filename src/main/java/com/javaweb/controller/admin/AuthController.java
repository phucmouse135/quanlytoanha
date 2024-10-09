package com.javaweb.controller.admin;

import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.request.AuthenticationRequest;
import com.javaweb.model.request.LoginRequest;
import com.javaweb.model.response.AuthenticationResponse;
import com.javaweb.repository.RoleRepository;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<String> sign_up(@Valid @RequestBody AuthenticationRequest authenticationRequest , BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid input");
        }
        if(!authenticationRequest.getPassword().equals(authenticationRequest.getRetypePassword())) {
            return ResponseEntity.badRequest().body("Password not match");
        }
        try {
            userService.register(authenticationRequest);
            return ResponseEntity.ok("Register successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/sign_in")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setJwtToken(token);
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}