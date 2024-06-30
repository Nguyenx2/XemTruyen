package com.example.xemtruyen.reponses.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class UserPageResponse {
    @JsonProperty("user_responses")
    List<UserResponse> userResponses;
    int mount;
}
