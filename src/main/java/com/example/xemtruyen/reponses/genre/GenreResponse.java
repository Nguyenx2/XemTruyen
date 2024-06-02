package com.example.xemtruyen.reponses.genre;

import com.example.xemtruyen.models.Story;
import com.example.xemtruyen.reponses.story.StoryResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreResponse {
    @JsonProperty("genre_id")
    private Long genreId;
    private String name;
    private String description;
}
