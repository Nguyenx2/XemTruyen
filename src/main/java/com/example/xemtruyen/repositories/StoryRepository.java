package com.example.xemtruyen.repositories;

import com.example.xemtruyen.models.Genre;
import com.example.xemtruyen.models.Story;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    boolean existsStoriesByTitle(String name);
    List<Story> findStoriesByGenresContains(Genre genre);

    @Query("SELECT s FROM Story s JOIN s.genres g WHERE " +
            "(:keyword IS NULL OR :keyword = ''" +
            "OR s.title LIKE CONCAT('%', :keyword, '%')) " +
            "AND (:authorId IS NULL OR :authorId = 0 OR s.author.authorId = :authorId)" +
            "AND (:genreId IS NULL OR :genreId = 0 OR g.id = :genreId)")
    List<Story> search(
            @Param("keyword") String keyword,
            @Param("authorId") Long authorId,
            @Param("genreId") Long genreId,
            Pageable pageable
    );

}
