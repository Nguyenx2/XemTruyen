package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.GenreDTO;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.genre.GenrePageResponse;
import com.example.xemtruyen.reponses.genre.GenreResponse;
import com.example.xemtruyen.services.genre.GenreService;
import com.example.xemtruyen.services.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CodeValue.CREATED;
import static com.example.xemtruyen.constant.Constant.CodeValue.SUCCESS;
import static com.example.xemtruyen.constant.Constant.CommonConstants.*;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_GENRE_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_GENRE_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final MessageService messageService;

    @PostMapping("")
    ApiResponse<GenreResponse> create(
            @RequestBody GenreDTO genreDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<GenreResponse>builder()
                .code(CREATED)
                .message(messageService.getMessage(CREATE_GENRE_SUCCESS, language))
                .data(genreService.create(genreDTO))
                .build();
    }

    @GetMapping("/details/{id}")
    ApiResponse<GenreResponse> details(
            @PathVariable Long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<GenreResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(genreService.details(id))
                .build();
    }

    @GetMapping("/list")
    ApiResponse<GenrePageResponse> list(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<GenrePageResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(genreService.list(keyword, page, size))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<GenreResponse> update(
            @PathVariable Long id,
            @RequestBody GenreDTO genreDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<GenreResponse>builder()
                .code(CREATED)
                .message(messageService.getMessage(UPDATE_GENRE_SUCCESS, language))
                .data(genreService.update(id, genreDTO))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(
            @PathVariable Long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        genreService.delete(id);
        return ApiResponse.<Void>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .build();
    }
}
