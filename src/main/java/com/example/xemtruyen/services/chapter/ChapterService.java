package com.example.xemtruyen.services.chapter;

import com.example.xemtruyen.dtos.ChapterDTO;
import com.example.xemtruyen.reponses.chapter.ChapterPageResponse;
import com.example.xemtruyen.reponses.chapter.ChapterResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ChapterService {
    ChapterResponse create(ChapterDTO chapterDTO);

    ChapterResponse update(Long id, ChapterDTO chapterDTO);

    ChapterPageResponse list();
    ChapterResponse details(Long id);
    void delete(Long id);
}
