package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerCommentHeart;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetUserTimerHeartBean {

    JpaTimerCommentHeartRepository jpaTimerCommentHeartRepository;

    @Autowired
    public GetUserTimerHeartBean(JpaTimerCommentHeartRepository jpaTimerCommentHeartRepository){
        this.jpaTimerCommentHeartRepository = jpaTimerCommentHeartRepository;
    }

    public Boolean checkUserHeart(UUID commentId, UUID userId){

        // 유저가 댓글에 누른 하트 찾기
        TimerCommentHeart timerCommentHeart = jpaTimerCommentHeartRepository.findByCommentIdAndUserId(commentId, userId);

        // 하트가 있으면 true 없으면 false
        return timerCommentHeart != null;
    }

}
