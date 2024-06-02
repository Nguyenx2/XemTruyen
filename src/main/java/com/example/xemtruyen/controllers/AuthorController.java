package com.example.xemtruyen.controllers;

import com.example.xemtruyen.constant.Constant;
import com.example.xemtruyen.dtos.AuthorDTO;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.author.AuthorPageResponse;
import com.example.xemtruyen.reponses.author.AuthorResponse;
import com.example.xemtruyen.services.author.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CommonConstants.SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_AUTHOR_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_AUTHOR_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    @GetMapping("")
    ApiResponse<AuthorPageResponse> list(
        @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ApiResponse.ofSuccess(
                SUCCESS,
                authorService.list(keyword, page, size)
        );
    }

    @GetMapping("/detail/{id}")
    ApiResponse<AuthorResponse> detail(@PathVariable Long id) {
        return ApiResponse.ofSuccess(
                SUCCESS,
                authorService.detail(id)
        );
    }

    @PostMapping("")
    ApiResponse<AuthorResponse> create(
            @Valid @RequestBody AuthorDTO authorDTO) {
        return ApiResponse.ofCreated(
                CREATE_AUTHOR_SUCCESS,
                authorService.create(authorDTO)
        );
    }

    @PutMapping("/{id}")
    ApiResponse<AuthorResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AuthorDTO authorDTO
    ) {
        return ApiResponse.ofCreated(
                UPDATE_AUTHOR_SUCCESS,
                authorService.update(id, authorDTO)
        );
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> detele(@PathVariable Long id) {
        authorService.delete(id);
        return ApiResponse.ofSuccess(
                SUCCESS, null
        );
    }

}
