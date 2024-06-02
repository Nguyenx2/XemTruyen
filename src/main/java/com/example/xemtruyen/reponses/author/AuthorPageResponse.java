package com.example.xemtruyen.reponses.author;

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
public class AuthorPageResponse {
    @JsonProperty("author_responses")
    List<AuthorResponse> authorResponses;
    int amount;
}
