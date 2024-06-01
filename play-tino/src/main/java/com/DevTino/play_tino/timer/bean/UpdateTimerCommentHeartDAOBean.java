package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateTimerCommentHeartDAOBean {
    JpaTimerCommentRepository jpaTimerCommentRepository;

    @Autowired
    public UpdateTimerCommentHeartDAOBean(JpaTimerCommentRepository jpaTimerCommentRepository){
        this.jpaTimerCommentRepository = jpaTimerCommentRepository;
    }

    //댓글 좋아요 추가
    public TimerComment commentHeartUp(UUID commentId) {

        // 댓글 아이디 가져오기
        TimerComment timerComment = jpaTimerCommentRepository.findById(commentId).get();

        // 아이디의 있는 하트카운트 올리기
        timerComment.setHeartCount(timerComment.getHeartCount()+1);

        return timerComment;
    }

    public void commentHeartDown(TimerComment timerComment){

        timerComment.setHeartCount(timerComment.getHeartCount()-1);

    }

}