package com.DevTino.play_tino.quiz.repository;

import com.DevTino.play_tino.quiz.domain.QuizCommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaQuizCommentHeartRepository extends JpaRepository<QuizCommentHeart, UUID> {
    QuizCommentHeart findByCommentIdAndUserId(UUID commentId, UUID userId);
    Boolean existsByCommentIdAndUserId(UUID commentId, UUID UserId);
}
