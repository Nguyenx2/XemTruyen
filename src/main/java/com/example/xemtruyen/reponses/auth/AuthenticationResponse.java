package com.example.xemtruyen.reponses.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private boolean authenticated;
}
