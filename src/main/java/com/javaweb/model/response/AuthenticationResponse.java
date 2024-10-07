package com.javaweb.model.response;

import lombok.Data;

@Data // Using lombok to generate getter and setter automatically for this class
public class AuthenticationResponse {
    private String jwtToken;

    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
