package com.DevTino.play_tino.quiz.repository;

import com.DevTino.play_tino.quiz.domain.QuizGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaQuizGameRepository extends JpaRepository<QuizGame, UUID> {
    QuizGame findByGameIdAndUserId(UUID gameId, UUID userId);
}
