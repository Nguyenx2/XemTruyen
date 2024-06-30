package com.example.xemtruyen.services.chapter;

import com.example.xemtruyen.dtos.ChapterDTO;
import com.example.xemtruyen.dtos.ChapterImageDTO;
import com.example.xemtruyen.exceptions.BadRequestException;
import com.example.xemtruyen.exceptions.ConflictException;
import com.example.xemtruyen.exceptions.DataNotFoundException;
import com.example.xemtruyen.models.Chapter;
import com.example.xemtruyen.models.ChapterImage;
import com.example.xemtruyen.models.Story;
import com.example.xemtruyen.reponses.chapter.ChapterPageResponse;
import com.example.xemtruyen.reponses.chapter.ChapterResponse;
import com.example.xemtruyen.repositories.ChapterRepository;
import com.example.xemtruyen.repositories.ChapterImageRepository;
import com.example.xemtruyen.repositories.StoryRepository;
import com.example.xemtruyen.utils.ConvertUtils;
import com.example.xemtruyen.utils.FileUtils;
import com.example.xemtruyen.utils.MapperUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {
    private final ChapterRepository chapterRepository;
    private final ChapterImageRepository chapterImageRepository;
    private final StoryRepository storyRepository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ChapterResponse create(ChapterDTO chapterDTO) {
        existsByChapterNumber(chapterDTO.getChapterNumber());
        Story story = storyRepository.findById(chapterDTO.getStoryId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find story with id = " + chapterDTO.getStoryId()));
        Chapter chapter = Chapter.builder()
                .chapterNumber(chapterDTO.getChapterNumber())
                .story(story)
                .build();
        chapterRepository.save(chapter);
        return MapperUtil.toResponse(chapter, ChapterResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ChapterResponse update(Long id, ChapterDTO chapterDTO) {
        Chapter chapter = findById(id);
        existsByChapterNumber(chapterDTO.getChapterNumber());
        chapter.setChapterNumber(chapterDTO.getChapterNumber());
        if (chapterDTO.getStoryId() != null) {
            chapter.setChapterId(chapter.getChapterId());
        }
        return MapperUtil.toResponse(chapter, ChapterResponse.class);
    }

    @Override
    public ChapterPageResponse list() {
        List<Chapter> chapters = chapterRepository.findAll();
        List<ChapterResponse> chapterResponses = MapperUtil.toDTOS(chapters, ChapterResponse.class);
        return ChapterPageResponse.of(chapterResponses, chapterResponses.size());
    }

    @Override
    public ChapterResponse details(Long id) {
        Chapter chapter = findById(id);
        return MapperUtil.toResponse(chapter, ChapterResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        deleteFromStorage(id);
        chapterRepository.deleteById(id);
    }

    private void existsByChapterNumber(int number) {
        if (chapterRepository.existsChapterByChapterNumber(number)) {
            throw new BadRequestException("Chapter already exists with number = " + number);
        }
    }

    private Chapter findById(Long id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find chapter with id = " + id));
    }

    private void deleteFromStorage(Long id) {
        Chapter chapter = findById(id);
        String storyName = ConvertUtils.convertToSlug(chapter.getStory().getTitle());
        String chapterNumber = "chapter-" + chapter.getChapterNumber();
        String folderPath = "uploads/" + storyName + "/" + chapterNumber;
        FileUtils.deleteFolder(folderPath);
    }
}
