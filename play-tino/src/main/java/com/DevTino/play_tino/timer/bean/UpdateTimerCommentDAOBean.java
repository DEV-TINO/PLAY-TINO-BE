package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.DTO.RequestTimerCommentUpdateDTO;
import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateTimerCommentDAOBean {

    JpaTimerCommentRepository jpaTimerCommentRepository;
    CheckUserTimerCommentBean checkUserTimerCommentBean;

    @Autowired
    public UpdateTimerCommentDAOBean(JpaTimerCommentRepository jpaTimerCommentRepository, CheckUserTimerCommentBean checkUserTimerCommentBean){
        this.jpaTimerCommentRepository = jpaTimerCommentRepository;
        this.checkUserTimerCommentBean = checkUserTimerCommentBean;
    }

    public TimerResponseSuccess exec(TimerComment timerComment, RequestTimerCommentUpdateDTO requestTimerCommentUpdateDTO){
        // responseSuccess 객체 생성
        TimerResponseSuccess timerResponseSuccess = new TimerResponseSuccess();

        // DAO의 userId와 입력한 userId가 일치하는지 확인
        if (checkUserTimerCommentBean.exec(timerComment, requestTimerCommentUpdateDTO.getCommentId()))
            timerResponseSuccess.setSuccess(true);
        else
            timerResponseSuccess.setSuccess(false);

        // 일치한다면 DAO에 저장
        if (timerResponseSuccess.getSuccess()){
            timerComment.setContent(requestTimerCommentUpdateDTO.getContent());
            jpaTimerCommentRepository.save(timerComment);
        }

        // 반환
        return timerResponseSuccess;

    }
}
