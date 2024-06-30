package com.example.xemtruyen.repositories;

import com.example.xemtruyen.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.chapter.chapterId = :chapterId")
    List<Comment> findByChapterId(@Param("chapterId") Long id);
}
