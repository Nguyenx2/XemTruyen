package com.example.xemtruyen.reponses.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @JsonProperty("user_id")
    private Long userId;

    private String email;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("is_active")
    private boolean isActive;
}
