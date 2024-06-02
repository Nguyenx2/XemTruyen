package com.example.xemtruyen.services.story;

import com.example.xemtruyen.dtos.StoryDTO;
import com.example.xemtruyen.reponses.story.StoryPageResponse;
import com.example.xemtruyen.reponses.story.StoryResponse;

public interface StoryService {
    StoryResponse create(StoryDTO storyDTO);
    StoryPageResponse list(String keyword, Long genreId, int size, int page);
    StoryResponse detail(Long id);

    StoryResponse update(Long id, StoryDTO storyDTO);
    void delete(Long id);
}
