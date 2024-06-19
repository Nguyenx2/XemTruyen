package com.example.xemtruyen.services.comment;

import com.example.xemtruyen.dtos.CommentDTO;
import com.example.xemtruyen.reponses.comment.CommentPageResponse;
import com.example.xemtruyen.reponses.comment.CommentResponse;

public interface CommentService {
    CommentResponse inserComment(CommentDTO commentDTO);

    void deleteComment(Long commentId);

    CommentResponse updateComment(Long id, CommentDTO commentDTO);

    CommentPageResponse getCommentsByChapter(Long chapterId);
}
