package com.DevTino.play_tino.quiz.repository;

import com.DevTino.play_tino.quiz.domain.QuizComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaQuizCommentRepository extends JpaRepository<QuizComment, UUID> {
     QuizComment findByCommentIdAndUserId(UUID commentId, UUID userId);

    Page<QuizComment> findAllByOrderByUploadTimeDesc(Pageable pageable);
    Page<QuizComment> findAllByOrderByHeartCountDescUploadTimeDesc(Pageable pageable);
}
