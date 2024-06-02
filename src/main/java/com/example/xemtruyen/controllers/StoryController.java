package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.StoryDTO;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.story.StoryPageResponse;
import com.example.xemtruyen.reponses.story.StoryResponse;
import com.example.xemtruyen.services.story.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CommonConstants.SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_STORY_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_STORY_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/stories")
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<StoryResponse> create(@ModelAttribute StoryDTO storyDTO) {
        return ApiResponse.ofCreated(
                CREATE_STORY_SUCCESS,
                storyService.create(storyDTO)
        );
    }

    @GetMapping
    ApiResponse<StoryPageResponse> list(
            @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
            @RequestParam(name = "genre_id", defaultValue = "", required = false) Long genreId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ApiResponse.ofSuccess(
                SUCCESS,
                storyService.list(keyword, genreId, page, size)
        );
    }

    @GetMapping("/detail/{id}")
    ApiResponse<StoryResponse> detail(@PathVariable Long id) {
        return ApiResponse.ofSuccess(
                SUCCESS,
                storyService.detail(id)
        );
    }

    @PutMapping("/{id}")
    ApiResponse<StoryResponse> update(
            @PathVariable Long id,
            StoryDTO storyDTO
    ) {
        return ApiResponse.ofCreated(
                UPDATE_STORY_SUCCESS,
                storyService.update(id, storyDTO)
        );
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(
            @PathVariable Long id
    ) {
        storyService.delete(id);
        return ApiResponse.ofSuccess(
                SUCCESS,
                null
        );
    }
}
