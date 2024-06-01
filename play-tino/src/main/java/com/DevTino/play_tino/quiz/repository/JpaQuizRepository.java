package com.DevTino.play_tino.quiz.repository;

import com.DevTino.play_tino.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaQuizRepository extends JpaRepository<Quiz, UUID> {
    @Query(value = "SELECT * FROM quiz WHERE category = ?1 ORDER BY rand() LIMIT 5", nativeQuery = true)
    List<Quiz> findAllByCategory(String category);
}
