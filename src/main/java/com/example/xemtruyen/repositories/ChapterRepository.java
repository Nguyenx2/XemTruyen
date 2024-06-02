package com.example.xemtruyen.repositories;

import com.example.xemtruyen.models.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    boolean existsChapterByChapterNumber(int number);
}
