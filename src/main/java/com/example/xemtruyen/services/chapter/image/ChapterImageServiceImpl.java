package com.example.xemtruyen.services.chapter.image;

import com.example.xemtruyen.dtos.ChapterImageDTO;
import com.example.xemtruyen.models.Chapter;
import com.example.xemtruyen.models.ChapterImage;
import com.example.xemtruyen.repositories.ChapterImageRepository;
import com.example.xemtruyen.repositories.ChapterRepository;
import com.example.xemtruyen.utils.ConvertUtils;
import com.example.xemtruyen.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
public class ChapterImageServiceImpl implements ChapterImageService {
    private final ChapterImageRepository chapterImageRepository;
    private final ChapterRepository chapterRepository;
    private static final String UPLOAD_DIR = "uploads/";
    private static final String NOT_FOUND_IMAGE = "";
    @Override
    public Resource get(String storyTitle, String chapterNumber, String imageName) {
        try {
            Path imagePath = Paths.get(UPLOAD_DIR + storyTitle + "/" + chapterNumber + "/" + imageName);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                Path notFoundPath = Paths.get(UPLOAD_DIR + NOT_FOUND_IMAGE);
                return new UrlResource(notFoundPath.toUri());
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<ChapterImage> uploadChapterImages(Long chapterId, List<MultipartFile> files) {
        files = files == null ? new ArrayList<>() : files;
        List<ChapterImage> chapterImages = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.getSize() == 0) {
                continue;
            }
            if (file.getSize() > 10 * 1024 * 1024) {
                throw new RuntimeException();
            }
            if (isImageFile(file)) {
                throw new RuntimeException();
            }
            Chapter chapter = findChapterById(chapterId);
            String fileName = storeFile(chapter, file);
            ChapterImage chapterImage = createImage(chapterId,
                    ChapterImageDTO.builder()
                            .chapterId(chapterId)
                            .imageUrl(fileName)
                            .build());
            chapterImages.add(chapterImage);
        }
        return chapterImages;
    }

    @Override
    public ChapterImage updateChapterImage(Long id, MultipartFile file) {
        ChapterImage chapterImage = findById(id);
        Chapter chapter = chapterImage.getChapter();
        deleteOldFileFromStorage(chapterImage);
        if (isImageFile(file)) {
            throw new RuntimeException();
        }
        String fileName = storeFile(chapter, file);
        chapterImage.setImageUrl(fileName);
        return chapterImageRepository.save(chapterImage);
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType == null || !contentType.startsWith("image/");
    }

    private String storeFile(Chapter chapter, MultipartFile file) {
        try {
            String storyName = ConvertUtils.convertToSlug(chapter.getStory().getTitle());
            String chapterNumber = "chapter-" + chapter.getChapterNumber();
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            // Add UUID
            String uniqueFilename = UUID.randomUUID() + "_" + filename;
            Path uploadDir = Paths.get("uploads", storyName, chapterNumber);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path destination = uploadDir.resolve(uniqueFilename);

            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return destination.toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private ChapterImage createImage(Long chapterId, ChapterImageDTO chapterImageDTO) {
        Chapter chapter = findChapterById(chapterId);
        ChapterImage chapterImage = ChapterImage.builder()
                .chapter(chapter)
                .imageUrl(chapterImageDTO.getImageUrl())
                .build();
        return chapterImageRepository.save(chapterImage);
    }

    private void deleteOldFileFromStorage(ChapterImage chapterImage) {
        FileUtils.deleteFile(chapterImage.getImageUrl());
    }

    private ChapterImage findById(Long id) {
        return chapterImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    private Chapter findChapterById(Long chapterId) {
        return chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException());
    }
}
