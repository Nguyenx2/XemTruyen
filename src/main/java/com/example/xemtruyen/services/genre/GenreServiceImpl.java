package com.example.xemtruyen.services.genre;

import com.example.xemtruyen.dtos.GenreDTO;
import com.example.xemtruyen.exceptions.BadRequestException;
import com.example.xemtruyen.exceptions.DataNotFoundException;
import com.example.xemtruyen.models.Genre;
import com.example.xemtruyen.models.Story;
import com.example.xemtruyen.reponses.genre.GenrePageResponse;
import com.example.xemtruyen.reponses.genre.GenreResponse;
import com.example.xemtruyen.repositories.GenreRepository;
import com.example.xemtruyen.repositories.StoryRepository;
import com.example.xemtruyen.utils.MapperUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final StoryRepository storyRepository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public GenreResponse create(GenreDTO genreDTO) {
        existsByName(genreDTO.getName());
        Genre genre = new Genre();
        genre.setName(genreDTO.getName());
        genre.setDescription(genreDTO.getDescription());
        return MapperUtil.toResponse(genreRepository.save(genre), GenreResponse.class);
    }

    @Override
    public GenrePageResponse list(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Genre> genres = genreRepository.search(keyword, pageable);
        List<GenreResponse> genreResponses = MapperUtil.toDTOS(genres, GenreResponse.class);
        return GenrePageResponse.of(genreResponses, genreResponses.size());
    }

    @Override
    public GenreResponse details(Long id) {
        Genre genre = findById(id);
        return MapperUtil.toResponse(genre, GenreResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public GenreResponse update(Long id, GenreDTO genreDTO) {
        Genre genre = findById(id);
        updateGenreFields(
                genre,
                genreDTO.getName(),
                genreDTO.getDescription()
        );
        return MapperUtil.toResponse(genreRepository.save(genre), GenreResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(long id) {
        Genre genre = findById(id);
        List<Story> stories = storyRepository.findStoriesByGenresContains(genre);
        if (!stories.isEmpty()) {
            throw new IllegalArgumentException("Cannot delete genre with associated stories");
        } else {
            genreRepository.deleteById(id);
        }
    }

    private void updateGenreFields(
            Genre genre,
            String genreName,
            String genreDescription
    ) {
        if (genreName != null) {
            genre.setName(genreName);
        }
        if (genreDescription != null) {
            genre.setDescription(genreDescription);
        }
    }

    private void existsByName(String name) {
        if (genreRepository.existsByName(name))
            throw new BadRequestException("Genre already exists with name = " + name);
    }

    private Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find genre with id = " + id));
    }
}
