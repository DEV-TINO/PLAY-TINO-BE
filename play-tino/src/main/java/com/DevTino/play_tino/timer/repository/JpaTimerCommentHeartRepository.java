package com.DevTino.play_tino.timer.repository;

import com.DevTino.play_tino.timer.domain.entity.TimerCommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTimerCommentHeartRepository extends JpaRepository<TimerCommentHeart, UUID> {
    TimerCommentHeart findByCommentIdAndUserId(UUID commentId, UUID userId);
}
