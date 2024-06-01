package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetTimerCommentDAOBean {

    JpaTimerCommentRepository jpaTimerCommentRepository;

    @Autowired
    public GetTimerCommentDAOBean(JpaTimerCommentRepository jpaTimerCommentRepository){
        this.jpaTimerCommentRepository = jpaTimerCommentRepository;
    }

    //commentId에 해당하는 댓글 찾아서 반환
    public TimerComment exec(UUID commentId){
        return jpaTimerCommentRepository.findById(commentId).get();
    }
}
