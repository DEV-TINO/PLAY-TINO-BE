package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerCommentHeart;
import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveTimerCommentHeartDAOBean {

    JpaTimerCommentHeartRepository jpaTimerCommentHeartRepository;
    CheckVaildTimerCommentHeartBean checkVaildTimerCommentHeartBean;

    @Autowired
    public SaveTimerCommentHeartDAOBean(JpaTimerCommentHeartRepository jpaTimerCommentHeartRepository, CheckVaildTimerCommentHeartBean checkVaildTimerCommentHeartBean){
        this.jpaTimerCommentHeartRepository = jpaTimerCommentHeartRepository;
        this.checkVaildTimerCommentHeartBean = checkVaildTimerCommentHeartBean;
    }

    public TimerResponseSuccess exec(TimerCommentHeart timerCommentHeart){

        TimerResponseSuccess timerResponseSuccess = new TimerResponseSuccess();

        // comment 값들이 유효한지 검사 후, 성공 여부 결정
        timerResponseSuccess.setSuccess(checkVaildTimerCommentHeartBean.exec(timerCommentHeart));

        // 성공한 경우 DAO저장
        if(timerResponseSuccess.getSuccess())
            jpaTimerCommentHeartRepository.save(timerCommentHeart);

        return timerResponseSuccess;
    }

}
