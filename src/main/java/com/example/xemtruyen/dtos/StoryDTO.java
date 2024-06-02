package com.example.xemtruyen.dtos;

import com.example.xemtruyen.models.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryDTO {
    @NotBlank(message = "Title's story is required")
    private String title;

    @NotBlank(message = "Summary's story is required")
    private String summary;

    private String status;

    private MultipartFile file;

    @NotNull(message = "Author's story is required")
    @JsonProperty("author_id")
    private Long authorId;

    @NotNull(message = "Genre's story is required")
    @JsonProperty("genre_ids")
    private List<Long> genreIds;
}
