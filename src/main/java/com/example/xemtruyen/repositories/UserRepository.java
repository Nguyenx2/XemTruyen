package com.example.xemtruyen.repositories;

import com.example.xemtruyen.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u WHERE " +
            "(:keyword IS NULL OR :keyword = ''" +
            "OR u.email LIKE CONCAT('%', :keyword, '%'))")
    List<User> search(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
