package com.example.xemtruyen.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {
    @NotBlank(message = "Email cannot be null")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "The password must have at least 6 characters")
    private String password;

    @JsonProperty("retype_password")
    private String retypePassword;

    @NotBlank
    @JsonProperty("full_name")
    private String fullName;
}
