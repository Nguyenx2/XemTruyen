package com.example.xemtruyen.services.story;

import com.example.xemtruyen.dtos.StoryDTO;
import com.example.xemtruyen.exceptions.BadRequestException;
import com.example.xemtruyen.exceptions.ConflictException;
import com.example.xemtruyen.exceptions.DataNotFoundException;
import com.example.xemtruyen.exceptions.InternalServerError;
import com.example.xemtruyen.models.Author;
import com.example.xemtruyen.models.Genre;
import com.example.xemtruyen.models.Status;
import com.example.xemtruyen.models.Story;
import com.example.xemtruyen.reponses.story.StoryPageResponse;
import com.example.xemtruyen.reponses.story.StoryResponse;
import com.example.xemtruyen.repositories.AuthorRepository;
import com.example.xemtruyen.repositories.GenreRepository;
import com.example.xemtruyen.repositories.StoryRepository;
import com.example.xemtruyen.utils.ConvertUtils;
import com.example.xemtruyen.utils.FileUtils;
import com.example.xemtruyen.utils.MapperUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {
    private final StoryRepository storyRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public StoryResponse create(StoryDTO storyDTO) {
        existsByStoryTitle(storyDTO.getTitle());
        Author existingAuthor = authorRepository.findById(storyDTO.getAuthorId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find author with id = " + storyDTO.getAuthorId()));
        Set<Genre> genres = mapGenreIdsToGenres(storyDTO.getGenreIds());
        Story story = Story.builder()
                .title(storyDTO.getTitle())
                .summary(storyDTO.getSummary())
                .status(Status.IN_PROGRESS)
                .author(existingAuthor)
                .genres(genres)
                .build();
        if (isImageFile(storyDTO.getFile())) {
            String coverImageUrl = storeFile(storyDTO.getFile());
            story.setCoverImageUrl(coverImageUrl);
        } else {
            throw new BadRequestException("File must be image !");
        }
        return MapperUtil.toResponse(storyRepository.save(story), StoryResponse.class);
    }

    @Override
    public StoryPageResponse list(String keyword, Long genreId, Long authorId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Story> stories = storyRepository.search(keyword, genreId, authorId, pageable);
        List<StoryResponse> storyResponses = MapperUtil.toDTOS(stories, StoryResponse.class);
        return StoryPageResponse.of(storyResponses, storyResponses.size());
    }

    @Override
    public StoryResponse details(Long id) {
        Story story = findById(id);
        return MapperUtil.toResponse(story, StoryResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public StoryResponse update(Long id, StoryDTO storyDTO) {
        Story story = findById(id);
        updateStoryFields(
                story,
                storyDTO.getTitle(),
                storyDTO.getSummary(),
                storyDTO.getStatus(),
                storyDTO.getFile(),
                storyDTO.getAuthorId(),
                storyDTO.getGenreIds()
        );
        return MapperUtil.toResponse(story, StoryResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        deleteFromStorage(id);
        Story story = findById(id);
        for (Genre genre : story.getGenres()) {
            genre.getStories().remove(story);
        }
        story.getGenres().clear();
        storyRepository.delete(story);
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    private Set<Genre> mapGenreIdsToGenres(List<Long> genreIds) {
        Set<Genre> genres = new HashSet<>();
        for (Long genreId : genreIds) {
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new DataNotFoundException("Cannot find genre with id = " + genreId));
            if (genre != null) {
                genres.add(genre);
            }
        }
        return genres;
    }

    private String storeFile(MultipartFile file) {
        try {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getName()));
            String uniqueFilename = UUID.randomUUID() + "_" + filename;
            Path uploadDir = Paths.get("uploads", "cover_images");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path destination = uploadDir.resolve(uniqueFilename);

            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return destination.toString();
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    private void existsByStoryTitle(String name) {
        if (storyRepository.existsStoriesByTitle(name)) {
            throw new RuntimeException();
        }
    }

    private Story findById(Long id) {
        return storyRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find story with id = " + id));
    }

    private void updateStoryFields(
            Story story,
            String title,
            String summary,
            String status,
            MultipartFile file,
            Long authorId,
            List<Long> genreIds
    ) {
        if (title != null) {
            story.setTitle(title);
        }
        if (summary != null) {
            story.setSummary(summary);
        }
        if (status != null) {
            story.setStatus(status);
        }
        if (file != null && isImageFile(file)) {
            String oldCoverImageUrl = story.getCoverImageUrl();
            FileUtils.deleteFile(oldCoverImageUrl);
            String coverImageUrl = storeFile(file);
            story.setCoverImageUrl(coverImageUrl);
        }
        if (authorId != null) {
            Author existingAuthor = authorRepository.findById(authorId)
                    .orElseThrow(() -> new DataNotFoundException("Cannot find author with id = " + authorId));
            story.setAuthor(existingAuthor);
        }

        if (genreIds != null) {
            Set<Genre> genres = mapGenreIdsToGenres(genreIds);
            story.setGenres(genres);
        }
    }

    private void deleteFromStorage(Long id) {
        Story story = findById(id);
        String storyName = ConvertUtils.convertToSlug(story.getTitle());
        String folderPath = "uploads/" + storyName;
        try {
            FileUtils.deleteFile(story.getCoverImageUrl());
            FileUtils.deleteFolder(folderPath);
        } catch (Exception e) {
            throw new ConflictException(e.getMessage());
        }
    }
}
