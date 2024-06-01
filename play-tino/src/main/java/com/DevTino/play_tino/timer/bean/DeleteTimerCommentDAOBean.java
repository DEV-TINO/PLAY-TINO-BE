package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.DTO.RequestTimerCommentDeleteDTO;
import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteTimerCommentDAOBean {

    JpaTimerCommentRepository jpaTimerCommentRepository;
    CheckUserTimerCommentBean checkUserTimerCommentBean;

    @Autowired
    public DeleteTimerCommentDAOBean(JpaTimerCommentRepository jpaTimerCommentRepository, CheckUserTimerCommentBean checkUserTimerCommentBean){
        this.jpaTimerCommentRepository = jpaTimerCommentRepository;
        this.checkUserTimerCommentBean = checkUserTimerCommentBean;
    }

    public TimerResponseSuccess exec(TimerComment timerComment, RequestTimerCommentDeleteDTO requestTimerCommentDeleteDTO){
        // responseSuccess 생성
        TimerResponseSuccess timerResponseSuccess = new TimerResponseSuccess();

        //댓글의 유저가 같은지 확인
        if(checkUserTimerCommentBean.exec(timerComment, requestTimerCommentDeleteDTO.getCommentId()))
            timerResponseSuccess.setSuccess(true);
        else timerResponseSuccess.setSuccess(false);

        //성공한 경우만 DAO 삭제
        if(timerResponseSuccess.getSuccess())
            jpaTimerCommentRepository.delete(timerComment);

        return timerResponseSuccess;
    }
}
