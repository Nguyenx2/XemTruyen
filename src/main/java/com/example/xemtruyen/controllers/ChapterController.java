package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.ChapterDTO;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.chapter.ChapterPageResponse;
import com.example.xemtruyen.reponses.chapter.ChapterResponse;
import com.example.xemtruyen.services.chapter.ChapterService;
import com.example.xemtruyen.services.message.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CodeValue.CREATED;
import static com.example.xemtruyen.constant.Constant.CodeValue.SUCCESS;
import static com.example.xemtruyen.constant.Constant.CommonConstants.*;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_CHAPTER_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_CHAPTER_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final MessageService messageService;

    @PostMapping("")
    ApiResponse<ChapterResponse> create(
            @Valid ChapterDTO chapterDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<ChapterResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(CREATE_CHAPTER_SUCCESS, language))
                .data(chapterService.create(chapterDTO))
                .build();
    }

    @GetMapping("")
    ApiResponse<ChapterPageResponse> list(
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<ChapterPageResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(chapterService.list())
                .build();
    }

    @GetMapping("/details/{id}")
    ApiResponse<ChapterResponse> details(
            @PathVariable Long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<ChapterResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(chapterService.details(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ChapterResponse> update(
            @PathVariable Long id, ChapterDTO chapterDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<ChapterResponse>builder()
                .code(CREATED)
                .message(messageService.getMessage(UPDATE_CHAPTER_SUCCESS, language))
                .data(chapterService.update(id, chapterDTO))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(
            @PathVariable Long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        chapterService.delete(id);
        return ApiResponse.<Void>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .build();
    }
}
