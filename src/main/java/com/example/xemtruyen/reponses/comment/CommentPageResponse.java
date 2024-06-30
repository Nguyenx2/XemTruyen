package com.example.xemtruyen.reponses.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class CommentPageResponse {
    @JsonProperty("comment_responses")
    List<CommentResponse> commentResponses;
    int mount;
}
