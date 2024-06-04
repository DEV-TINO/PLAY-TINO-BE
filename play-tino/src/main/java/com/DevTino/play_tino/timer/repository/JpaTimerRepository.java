package com.DevTino.play_tino.timer.repository;

import com.DevTino.play_tino.timer.domain.entity.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaTimerRepository extends JpaRepository<Timer, UUID> {

    // gameId와 userId를 이용해 Timer 찾기
    Timer findByGameIdAndUserId(UUID gameId, UUID userId);

    List<Timer> findByGameIdInOrderByErrorRangeAscUploadTimeAsc(List<UUID> timerIds);

    List<Timer> findTop100ByOrderByErrorRangeAsc();
}
