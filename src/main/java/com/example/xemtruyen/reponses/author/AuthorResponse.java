package com.example.xemtruyen.reponses.author;

import com.example.xemtruyen.models.Story;
import com.example.xemtruyen.reponses.story.StoryResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class AuthorResponse {
    @JsonProperty("author_id")
    private Long authorId;

    @JsonProperty("author_name")
    private String authorName;
}
