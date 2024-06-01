package com.DevTino.play_tino.timer.repository;

import com.DevTino.play_tino.timer.domain.entity.TimerRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTimerRankRepository extends JpaRepository<TimerRank, UUID> {

    TimerRank findByTimerIdAndUserId(UUID timerId, UUID userId);
}
