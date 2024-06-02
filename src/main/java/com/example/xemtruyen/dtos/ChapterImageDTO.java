package com.example.xemtruyen.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterImageDTO {
    @NotNull(message = "Chapter ID is required")
    @JsonProperty("chapter_id")
    private Long chapterId;

    @NotBlank(message = "Image url is required")
    @JsonProperty("image_url")
    private String imageUrl;
}
