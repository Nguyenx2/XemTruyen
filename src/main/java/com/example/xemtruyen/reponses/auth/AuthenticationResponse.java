package com.example.xemtruyen.reponses.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
}
