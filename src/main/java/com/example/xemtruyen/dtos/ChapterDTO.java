package com.example.xemtruyen.dtos;

import com.example.xemtruyen.models.Story;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {
    @Min(value = 1, message = "Chapter number must be greater than or equal to 1")
    @JsonProperty("chapter_number")
    private int chapterNumber;

    @NotNull(message = "Story ID is required")
    @JsonProperty("story_id")
    private Long storyId;
}
