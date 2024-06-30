package com.example.xemtruyen.services.genre;

import com.example.xemtruyen.dtos.GenreDTO;
import com.example.xemtruyen.reponses.genre.GenrePageResponse;
import com.example.xemtruyen.reponses.genre.GenreResponse;

public interface GenreService {
    GenreResponse create(GenreDTO genreDTO);
    GenrePageResponse list(String keyword, int page, int size);
    GenreResponse details(Long id);
    GenreResponse update(Long id, GenreDTO genreDTO);
    void delete(long id);
}
