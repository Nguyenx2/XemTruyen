package com.example.xemtruyen.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @NotBlank(message = "Old password is required")
    private String password;

    @NotBlank(message = "New password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @JsonProperty("new_password")
    private String newPassword;

    @NotBlank(message = "Retype password is required")
    @Size(min = 6)
    @JsonProperty("confirm_password")
    private String confirmPassword;
}
