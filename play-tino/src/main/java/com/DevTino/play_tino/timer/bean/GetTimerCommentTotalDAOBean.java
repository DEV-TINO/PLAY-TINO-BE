package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.repository.JpaTimerCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetTimerCommentTotalDAOBean {
    JpaTimerCommentRepository jpaTimerCommentRepository;

    @Autowired

    public GetTimerCommentTotalDAOBean(JpaTimerCommentRepository jpaTimerCommentRepository) {
        this.jpaTimerCommentRepository = jpaTimerCommentRepository;
    }

    public Integer exec(){
        return jpaTimerCommentRepository.findAll().size();
    }
}
