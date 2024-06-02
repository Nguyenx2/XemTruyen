package com.example.xemtruyen.controllers;

import com.example.xemtruyen.models.Chapter;
import com.example.xemtruyen.models.ChapterImage;
import com.example.xemtruyen.models.Story;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.services.chapter.image.ChapterImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.xemtruyen.constant.Constant.CommonConstants.SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_CHAPTER_IMAGES_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_CHAPTER_IMAGE_SUCCESS;

@RestController
@RequestMapping("${api.prefix}")
@RequiredArgsConstructor
public class ChapterImageController {
    private final ChapterImageService chapterImageService;

    @PostMapping(value = "uploads/{chapterId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<List<ChapterImage>> uploadChapterImages(
            @PathVariable Long chapterId,
            @ModelAttribute("files") List<MultipartFile> files) {
        return ApiResponse.ofCreated(
                CREATE_CHAPTER_IMAGES_SUCCESS,
                chapterImageService.uploadChapterImages(chapterId, files)
        );
    }

    @GetMapping("/{storyTitle}/{chapterNumber}/{imageName}")
    public ApiResponse<Resource> get (
            @PathVariable String storyTitle,
            @PathVariable String chapterNumber,
            @PathVariable String imageName
            ) {
        return ApiResponse.ofSuccess(
                SUCCESS,
                chapterImageService.get(storyTitle, chapterNumber, imageName)
        );
    }

    @PostMapping(value = "uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ChapterImage> updateChapterImage(
            @PathVariable Long id,
            @ModelAttribute("file") MultipartFile file
    ) {
        return ApiResponse.ofCreated(
                UPDATE_CHAPTER_IMAGE_SUCCESS,
                chapterImageService.updateChapterImage(id, file)
        );
    }
}

