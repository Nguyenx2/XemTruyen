package com.example.xemtruyen.services.author;

import com.example.xemtruyen.dtos.AuthorDTO;
import com.example.xemtruyen.exceptions.ConflictException;
import com.example.xemtruyen.exceptions.DataNotFoundException;
import com.example.xemtruyen.models.Author;
import com.example.xemtruyen.models.Story;
import com.example.xemtruyen.reponses.author.AuthorPageResponse;
import com.example.xemtruyen.reponses.author.AuthorResponse;
import com.example.xemtruyen.repositories.AuthorRepository;
import com.example.xemtruyen.services.author.AuthorService;
import com.example.xemtruyen.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    @Override
    public AuthorPageResponse list(
            String keyword,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        List<Author> authors = authorRepository.search(keyword, pageable);
        List<AuthorResponse> authorResponses = MapperUtil.toDTOS(authors, AuthorResponse.class);
        return AuthorPageResponse.of(authorResponses, authorResponses.size());
    }

    @Override
    public AuthorResponse create(AuthorDTO authorDTO) {
        existsByName(authorDTO.getAuthorName());
        Author newAuthor = MapperUtil.toModel(authorDTO, Author.class);
        return MapperUtil.toResponse(authorRepository.save(newAuthor), AuthorResponse.class);
    }

    @Override
    public AuthorResponse update(Long id, AuthorDTO authorDTO) {
        Author author = findById(id);
        updateAuthorField(
                author,
                authorDTO.getAuthorName()
        );
        return MapperUtil.toResponse(authorRepository.save(author), AuthorResponse.class);
    }

    @Override
    public AuthorResponse detail(Long id) {
        Author author = findById(id);
        return MapperUtil.toResponse(author, AuthorResponse.class);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    private void updateAuthorField(
            Author author,
            String authorName
    ) {
        if (authorName != null) {
            author.setAuthorName(authorName);
        }
    }
    private void existsByName(String name) {
        if (authorRepository.existsAuthorByAuthorName(name)) {
            throw new ConflictException("Author already exists with name = " + name);
        }
    }

    private Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find author with id =" + id));
    }
}
