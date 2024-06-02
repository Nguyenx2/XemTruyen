package com.example.xemtruyen.services.author;

import com.example.xemtruyen.dtos.AuthorDTO;
import com.example.xemtruyen.reponses.author.AuthorPageResponse;
import com.example.xemtruyen.reponses.author.AuthorResponse;

public interface AuthorService {
    AuthorPageResponse list(String keyword, int size, int page);

    AuthorResponse create(AuthorDTO authorDTO);

    AuthorResponse update(Long id, AuthorDTO authorDTO);
    AuthorResponse detail(Long id);
    void delete(Long id);

}
