package com.example.xemtruyen.reponses.story;

import com.example.xemtruyen.models.Author;
import com.example.xemtruyen.models.Chapter;
import com.example.xemtruyen.models.Genre;
import com.example.xemtruyen.models.Status;
import com.example.xemtruyen.reponses.author.AuthorResponse;
import com.example.xemtruyen.reponses.chapter.ChapterResponse;
import com.example.xemtruyen.reponses.genre.GenreResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoryResponse {
    @JsonProperty("story_id")
    private Long storyId;

    private String title;

    private String summary;

    private String status;

    private AuthorResponse author;

    private Set<GenreResponse> genres;

    @JsonProperty("cover_image_url")
    private String coverImageUrl;

    @JsonProperty("date_created")
    private LocalDateTime dateCreated;

    @JsonProperty("date_updated")
    private LocalDateTime dateUpdated;

}
