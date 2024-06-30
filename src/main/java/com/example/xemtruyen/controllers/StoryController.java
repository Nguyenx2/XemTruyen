package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.StoryDTO;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.story.StoryPageResponse;
import com.example.xemtruyen.reponses.story.StoryResponse;
import com.example.xemtruyen.services.message.MessageService;
import com.example.xemtruyen.services.story.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CodeValue.CREATED;
import static com.example.xemtruyen.constant.Constant.CodeValue.SUCCESS;
import static com.example.xemtruyen.constant.Constant.CommonConstants.*;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_STORY_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_STORY_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/stories")
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;
    private final MessageService messageService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<StoryResponse> create(
            @ModelAttribute StoryDTO storyDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<StoryResponse>builder()
                .code(CREATED)
                .message(messageService.getMessage(CREATE_STORY_SUCCESS, language))
                .data(storyService.create(storyDTO))
                .build();
    }

    @GetMapping("/list")
    ApiResponse<StoryPageResponse> list(
            @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
            @RequestParam(name = "genre_id", defaultValue = "", required = false) Long genreId,
            @RequestParam(name = "author_id", defaultValue = "", required = false) Long authorId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<StoryPageResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(storyService.list(keyword, genreId, authorId, page, size))
                .build();
    }

    @GetMapping("/details/{id}")
    ApiResponse<StoryResponse> details(
            @PathVariable Long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<StoryResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(storyService.details(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<StoryResponse> update(
            @PathVariable Long id,
            StoryDTO storyDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<StoryResponse>builder()
                .code(CREATED)
                .message(messageService.getMessage(UPDATE_STORY_SUCCESS, language))
                .data(storyService.update(id, storyDTO))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(
            @PathVariable Long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        storyService.delete(id);
        return ApiResponse.<Void>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .build();
    }
}
