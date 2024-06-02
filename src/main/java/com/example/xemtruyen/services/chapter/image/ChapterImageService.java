package com.example.xemtruyen.services.chapter.image;

import com.example.xemtruyen.models.ChapterImage;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ChapterImageService {
    Resource get(String storyTitle, String chapterNumber, String imageName);

    List<ChapterImage> uploadChapterImages(Long chapterId, List<MultipartFile> files);
    ChapterImage updateChapterImage(Long id, MultipartFile file);
}
