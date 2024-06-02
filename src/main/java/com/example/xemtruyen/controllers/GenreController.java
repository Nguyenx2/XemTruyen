package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.GenreDTO;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.genre.GenrePageResponse;
import com.example.xemtruyen.reponses.genre.GenreResponse;
import com.example.xemtruyen.services.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CommonConstants.SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_GENRE_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_GENRE_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @PostMapping("")
    ApiResponse<GenreResponse> create(@RequestBody GenreDTO genreDTO) {
        return ApiResponse.ofCreated(
                CREATE_GENRE_SUCCESS,
                genreService.create(genreDTO)
        );
    }

    @GetMapping("/{id}")
    ApiResponse<GenreResponse> detail(@PathVariable Long id) {
         return ApiResponse.ofSuccess(
                SUCCESS,
                genreService.detail(id)
         );
    }

    @GetMapping("")
    ApiResponse<GenrePageResponse> list(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ApiResponse.ofSuccess(
                SUCCESS,
                genreService.list(keyword, page, size)
        );
    }

    @PutMapping("/{id}")
    ApiResponse<GenreResponse> update(
            @PathVariable Long id,
            @RequestBody GenreDTO genreDTO) {
        return ApiResponse.ofCreated(
                UPDATE_GENRE_SUCCESS,
                genreService.update(id, genreDTO)
        );
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable Long id) {
        genreService.delete(id);
        return ApiResponse.ofSuccess(
                SUCCESS,
                null
        );
    }
}
