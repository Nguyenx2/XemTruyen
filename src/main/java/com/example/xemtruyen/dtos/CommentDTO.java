package com.example.xemtruyen.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    @JsonProperty("chapter_id")
    private Long chapterId;

    @JsonProperty("user_id")
    private Long userId;

    private String content;
}
