package com.example.xemtruyen.controllers;

import com.example.xemtruyen.dtos.CommentDTO;
import com.example.xemtruyen.reponses.ApiResponse;
import com.example.xemtruyen.reponses.comment.CommentPageResponse;
import com.example.xemtruyen.reponses.comment.CommentResponse;
import com.example.xemtruyen.services.comment.CommentService;
import com.example.xemtruyen.services.message.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.xemtruyen.constant.Constant.CodeValue.SUCCESS;
import static com.example.xemtruyen.constant.Constant.CommonConstants.*;
import static com.example.xemtruyen.constant.Constant.MessageException.INSERT_COMMENT_SUCCESS;
import static com.example.xemtruyen.constant.Constant.MessageException.UPDATE_COMMENT_SUCCESS;

@RestController
@RequestMapping("${api.prefix}/comments")
@RequiredArgsConstructor
public class CommentController {

    private CommentService commentService;
    private MessageService messageService;

    @GetMapping("/{chapterId}")
    public ApiResponse<CommentPageResponse> list(
            @PathVariable Long chapterId,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<CommentPageResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .data(commentService.getCommentsByChapter(chapterId))
                .build();
    }

    @PostMapping("")
    public ApiResponse<CommentResponse> insertComment(
            @Valid CommentDTO commentDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        return ApiResponse.<CommentResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(INSERT_COMMENT_SUCCESS, language))
                .data(commentService.inserComment(commentDTO))
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<CommentResponse> updateComment(
            @PathVariable Long id,
            @Valid CommentDTO commentDTO,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ApiResponse.<CommentResponse>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(UPDATE_COMMENT_SUCCESS, language))
                .data(commentService.updateComment(id, commentDTO))
                .build();
    }

    @DeleteMapping("{id}")
    public ApiResponse<Void> delete(
            @PathVariable Long id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        commentService.deleteComment(id);
        return ApiResponse.<Void>builder()
                .code(SUCCESS)
                .message(messageService.getMessage(SUCCESSFULLY, language))
                .build();
    }

}
