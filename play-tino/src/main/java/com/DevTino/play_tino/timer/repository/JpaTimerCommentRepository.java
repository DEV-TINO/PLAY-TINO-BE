package com.DevTino.play_tino.timer.repository;

import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaTimerCommentRepository extends JpaRepository<TimerComment, UUID> {
    // 업로드 시간 기준 내림차순으로 정률 후 comment 찾기
    List<TimerComment> findAllByOrderByUploadTimeDesc();
    // 하트수 내림차순으로 정렬 후 comment 찾기
    List<TimerComment> findAllByOrderByHeartCountDescUploadTimeDesc();
    // page형으로 가져오기
    Page<TimerComment> findAll(Pageable pageable);
}
