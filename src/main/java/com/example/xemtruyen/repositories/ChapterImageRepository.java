package com.example.xemtruyen.repositories;

import com.example.xemtruyen.models.ChapterImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterImageRepository extends JpaRepository<ChapterImage, Long> {
    List<ChapterImage> findChapterImagesByChapter_ChapterId(Long id);
}
