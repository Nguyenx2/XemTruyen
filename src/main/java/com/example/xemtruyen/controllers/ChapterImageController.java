package com.example.xemtruyen.controllers;

import com.example.xemtruyen.models.ChapterImage;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.services.chapter.image.ChapterImageService;
import com.example.xemtruyen.services.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.xemtruyen.constant.Constant.CodeValue.CREATED;
import static com.example.xemtruyen.constant.Constant.CodeValue.SUCCESS;
import static com.example.xemtruyen.constant.Constant.CommonConstants.*;
import static com.example.xemtruyen.constant.Constant.MessageException.CREATE_CHAPTER_IMAGES_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/chapter-images")
@RequiredArgsConstructor
public class ChapterImageController {

    private final ChapterImageService chapterImageService;
    private final MessageService messageService;

    @PostMapping(value = "/uploads/{chapterId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<List<ChapterImage>> uploadChapterImages(
            @PathVariable Long chapterId,
            @ModelAttribute("files") List<MultipartFile> files,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<List<ChapterImage>>builder()
                .code(CREATED)
                .message(messageService.getMessage(CREATE_CHAPTER_IMAGES_SUCCESS, language))
                .data(chapterImageService.uploadChapterImages(chapterId, files))
                .build();
    }

    @GetMapping("/{chapterId}/{imageName}")
    public ApiResponse<Resource> get(
            @PathVariable Long chapterId,
            @PathVariable String imageName,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<Resource>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(chapterImageService.get(chapterId, imageName))
                .build();
    }
}

