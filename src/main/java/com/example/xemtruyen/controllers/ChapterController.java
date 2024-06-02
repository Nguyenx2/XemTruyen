package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.ChapterDTO;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.chapter.ChapterPageResponse;
import com.example.xemtruyen.reponses.chapter.ChapterResponse;
import com.example.xemtruyen.services.chapter.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CommonConstants.SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_CHAPTER_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_CHAPTER_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/chapters")
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;

    @PostMapping("")
    ApiResponse<ChapterResponse> create(@Valid ChapterDTO chapterDTO) {
        return ApiResponse.ofCreated(
                CREATE_CHAPTER_SUCCESS,
                chapterService.create(chapterDTO)
        );
    }

    @GetMapping("")
    ApiResponse<ChapterPageResponse> list() {
        return ApiResponse.ofSuccess(
                SUCCESS,
                chapterService.list()
        );
    }

    @GetMapping("/detail/{id}")
    ApiResponse<ChapterResponse> detail(@PathVariable Long id) {
        return ApiResponse.ofSuccess(
                SUCCESS,
                chapterService.detail(id)
        );
    }

    @PutMapping("/{id}")
    ApiResponse<ChapterResponse> update(@PathVariable Long id, ChapterDTO chapterDTO) {
        return ApiResponse.ofSuccess(
                UPDATE_CHAPTER_SUCCESS,
                chapterService.update(id, chapterDTO)
        );
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable Long id) {
        chapterService.delete(id);
        return ApiResponse.ofSuccess(
                SUCCESS,
                null
        );
    }
}
