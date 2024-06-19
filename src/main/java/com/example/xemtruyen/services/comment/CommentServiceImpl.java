package com.example.xemtruyen.services.comment;

import com.example.xemtruyen.dtos.CommentDTO;
import com.example.xemtruyen.exceptions.DataNotFoundException;
import com.example.xemtruyen.models.Chapter;
import com.example.xemtruyen.models.Comment;
import com.example.xemtruyen.models.User;
import com.example.xemtruyen.reponses.comment.CommentPageResponse;
import com.example.xemtruyen.reponses.comment.CommentResponse;
import com.example.xemtruyen.reponses.story.StoryResponse;
import com.example.xemtruyen.repositories.ChapterRepository;
import com.example.xemtruyen.repositories.CommentRepository;
import com.example.xemtruyen.repositories.UserRepository;
import com.example.xemtruyen.utils.MapperUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ChapterRepository chapterRepository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public CommentResponse inserComment(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId()).orElse(null);
        Chapter chapter = chapterRepository.findById(commentDTO.getChapterId()).orElse(null);
        if (user == null || chapter == null) {
            throw new DataNotFoundException("Cannot find data");
        }
        Comment comment = Comment.builder()
                .user(user)
                .chapter(chapter)
                .content(commentDTO.getContent())
                .build();
        return MapperUtil.toModel(commentRepository.save(comment), CommentResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @PostAuthorize("returnObject.email == authentication.name")
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    @PostAuthorize("returnObject.email == authentication.name")
    public CommentResponse updateComment(Long id, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find comment with id = " + id));
        comment.setContent(commentDTO.getContent());
        return MapperUtil.toResponse(commentRepository.save(comment), CommentResponse.class);
    }

    @Override
    public CommentPageResponse getCommentsByChapter(Long chapterId) {
        List<Comment> comments = commentRepository.findByChapterId(chapterId);
        List<CommentResponse> commentResponses = MapperUtil.toDTOS(comments, CommentResponse.class);
        return CommentPageResponse.of(commentResponses, commentResponses.size());
    }
}
