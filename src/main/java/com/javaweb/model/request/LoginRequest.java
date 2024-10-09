package com.javaweb.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @JsonProperty("username")
    @NotBlank(message = "username is required")
    private String username;
    @JsonProperty("password")
    @NotBlank(message = "Password can not be blank")
    private String password;
}
