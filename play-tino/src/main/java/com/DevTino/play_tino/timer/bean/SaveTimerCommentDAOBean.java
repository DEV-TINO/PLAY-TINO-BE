package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveTimerCommentDAOBean {

    JpaTimerCommentRepository jpaTimerCommentRepository;
    CheckVaildTimerCommentBean checkVaildTimerCommentBean;

    @Autowired
    public SaveTimerCommentDAOBean(JpaTimerCommentRepository jpaTimerCommentRepository, CheckVaildTimerCommentBean checkVaildTimerCommentBean){
        this.jpaTimerCommentRepository = jpaTimerCommentRepository;
        this.checkVaildTimerCommentBean = checkVaildTimerCommentBean;
    }

    //댓글 저장
    public TimerResponseSuccess exec(TimerComment timerComment){
        // responseSuccess 객체 생성
            TimerResponseSuccess timerResponseSuccess = new TimerResponseSuccess();

            // comment 값들이 유효한지 검사 후, 성공 여부 결정
            timerResponseSuccess.setSuccess(checkVaildTimerCommentBean.exec(timerComment));

            // 성공한 경우 DAO저장
            if(timerResponseSuccess.getSuccess())
                jpaTimerCommentRepository.save(timerComment);

            return timerResponseSuccess;

    }

}