package com.example.xemtruyen.dtos;

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

    @NotBlank
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("is_active")
    private boolean isActive;
}
