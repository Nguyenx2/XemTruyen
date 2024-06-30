package com.example.xemtruyen.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
