package com.example.xemtruyen.reponses.comment;

import com.example.xemtruyen.reponses.user.UserResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("user")
    private UserResponse user;

    @JsonProperty("chapter_id")
    private Long chapterId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
