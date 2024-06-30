package com.example.xemtruyen.repositories;

import com.example.xemtruyen.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("SELECT t FROM Token t join User u ON t.user.userId = u.userId WHERE " +
            "u.userId = :userId " +
            "AND (t.expired = false OR t.revoked = false )")
    List<Token> findAllValidTokensByUser(@Param("userId") Long userId);

    Optional<Token> findByToken(String token);
}
