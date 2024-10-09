package com.javaweb.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Getter
public class AuthenticationRequest {
    @JsonProperty("username")
    @NotBlank(message = "Username is required")
    private String username;
    @JsonProperty("password")
    @NotBlank(message = "Password is required")
    private String password;
    @JsonProperty("retype_password")
    @NotBlank(message = "Retype password is required")
    private String retypePassword;
    @NotNull(message = "Role code is required")
    @JsonProperty("role_code")
    private String rolecode;

    @JsonProperty("full_name")
    @NotBlank(message = "Full name is required")
    private String fullName;

    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    private String email;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password, String rolecode, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.retypePassword = retypePassword;
        this.rolecode = rolecode;
        this.fullName = fullName;
        this.email = email;
    }
}
