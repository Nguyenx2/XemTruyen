package com.example.xemtruyen.reponses.story;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class StoryPageResponse {
    @JsonProperty("story_responses")
    List<StoryResponse> storyResponses;
    int mount;
}
