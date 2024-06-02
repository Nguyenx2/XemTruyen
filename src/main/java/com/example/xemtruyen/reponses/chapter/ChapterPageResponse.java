package com.example.xemtruyen.reponses.chapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ChapterPageResponse {
    private List<ChapterResponse> chapterResponses;
    int mount;
}
