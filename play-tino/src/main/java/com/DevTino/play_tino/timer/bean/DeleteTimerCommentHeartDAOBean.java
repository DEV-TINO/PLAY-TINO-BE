package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerCommentHeart;
import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteTimerCommentHeartDAOBean {

    JpaTimerCommentHeartRepository jpaTimerCommentHeartRepository;

    @Autowired
    public DeleteTimerCommentHeartDAOBean(JpaTimerCommentHeartRepository jpaTimerCommentHeartRepository){
        this.jpaTimerCommentHeartRepository = jpaTimerCommentHeartRepository;
    }

    public TimerResponseSuccess exec(TimerCommentHeart timerCommentHeart){

        // 객체 생성
        TimerResponseSuccess timerResponseSuccess = new TimerResponseSuccess();

        // 변수에 true 삽입
        timerResponseSuccess.setSuccess(true);

        // 성공한 경우만 DAO 삭제
        jpaTimerCommentHeartRepository.delete(timerCommentHeart);

        return timerResponseSuccess;
    }

}
