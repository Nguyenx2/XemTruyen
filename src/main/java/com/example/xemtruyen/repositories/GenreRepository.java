package com.example.xemtruyen.repositories;

import com.example.xemtruyen.models.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByName(String name);

    @Query("SELECT g FROM Genre g WHERE " +
            "(:keyword IS NULL OR :keyword = '' " +
            "OR g.name LIKE CONCAT('%', :keyword, '%') " +
            "OR g.description LIKE CONCAT('%', :keyword, '%'))")
    List<Genre> search (
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
