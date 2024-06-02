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

    @Query("SELECT s FROM Story s WHERE " +
            "(:keyword IS NULL OR :keyword = ''" +
            "OR s.title LIKE CONCAT('%', :keyword, '%'))")
    List<Story> search(
            @Param("keyword") String keyword,
            Pageable pageable
    );

}
