package com.javaweb.model.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String userName;
    private String password;

    public AuthenticationRequest() {
    }
}
