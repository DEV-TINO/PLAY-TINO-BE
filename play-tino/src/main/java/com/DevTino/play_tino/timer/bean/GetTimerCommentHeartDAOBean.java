package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerCommentHeart;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetTimerCommentHeartDAOBean {
    JpaTimerCommentHeartRepository jpaTimerCommentHeartRepository;

    @Autowired
    public GetTimerCommentHeartDAOBean(JpaTimerCommentHeartRepository jpaTimerCommentHeartRepository){
        this.jpaTimerCommentHeartRepository = jpaTimerCommentHeartRepository;
    }

    public TimerCommentHeart exec(UUID commentId, UUID userId){
        return jpaTimerCommentHeartRepository.findByCommentIdAndUserId(commentId, userId);
    }
}
