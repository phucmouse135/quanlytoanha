package com.javaweb.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Using lombok to generate getter and setter automatically for this class
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwtToken;
}
