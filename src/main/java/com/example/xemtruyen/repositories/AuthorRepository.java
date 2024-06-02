package com.example.xemtruyen.repositories;

import com.example.xemtruyen.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsAuthorByAuthorName(String name);

    @Query("SELECT a FROM Author a WHERE " +
            "(:keyword IS NULL OR :keyword = ''" +
            "OR a.authorName LIKE CONCAT('%', :keyword, '%') )")
    List<Author> search(
            @Param("keyword")String keyword,
            Pageable pageable
    );
}
