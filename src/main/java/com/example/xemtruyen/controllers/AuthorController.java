package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.AuthorDTO;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.author.AuthorPageResponse;
import com.example.xemtruyen.reponses.author.AuthorResponse;
import com.example.xemtruyen.services.author.AuthorService;
import com.example.xemtruyen.services.message.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CodeValue.SUCCESS;
import static com.example.xemtruyen.constant.Constant.CommonConstants.*;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_AUTHOR_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_AUTHOR_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final MessageService messageService;

    @GetMapping("")
    ApiResponse<AuthorPageResponse> list(
            @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<AuthorPageResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(authorService.list(keyword, page, size))
                .build();
    }

    @GetMapping("/details/{id}")
    ApiResponse<AuthorResponse> details(
            @PathVariable Long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<AuthorResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(authorService.details(id))
                .build();
    }

    @PostMapping("")
    ApiResponse<AuthorResponse> create(
            @Valid @RequestBody AuthorDTO authorDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<AuthorResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(CREATE_AUTHOR_SUCCESS, language))
                .data(authorService.create(authorDTO))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<AuthorResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AuthorDTO authorDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<AuthorResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(UPDATE_AUTHOR_SUCCESS, language))
                .data(authorService.update(id, authorDTO))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> detele(
            @PathVariable Long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        authorService.delete(id);
        return ApiResponse.<Void>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .build();
    }

}
