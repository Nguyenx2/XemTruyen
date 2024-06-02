package com.example.xemtruyen.reponses.genre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GenrePageResponse {
    @JsonProperty("genre_response")
    List<GenreResponse> genreResponses;

    int mount;
}
