package com.DevTino.play_tino.quiz.repository;

import com.DevTino.play_tino.quiz.domain.QuizRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaQuizRankRepository extends JpaRepository<QuizRank, UUID> {
    QuizRank findByGameIdAndUserId(UUID gameId, UUID userId);

    Page<QuizRank> findTop100ByOrderByAllCorrectDescUpdateAt(Pageable pageable);

    List<QuizRank> findTop100ByOrderByAllCorrectDescUpdateAtDesc();

}
