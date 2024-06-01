package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import com.DevTino.play_tino.timer.domain.entity.Timer;
import com.DevTino.play_tino.timer.repository.JpaTimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveTimerDAOBean {

    JpaTimerRepository jpaTimerRepository;
    CheckVaildTimerBean checkVaildTimerBean;

    @Autowired
    public SaveTimerDAOBean(JpaTimerRepository jpaTimerRepository, CheckVaildTimerBean checkVaildTimerBean){
        this.jpaTimerRepository = jpaTimerRepository;
        this.checkVaildTimerBean = checkVaildTimerBean;
    }

    public TimerResponseSuccess exec(Timer timer){

        // responseSuccess 객체 생성
        TimerResponseSuccess timerResponseSuccess = new TimerResponseSuccess();

        // comment 값들이 유효한지 검사 후, 성공 여부 결정
        timerResponseSuccess.setSuccess(checkVaildTimerBean.exec(timer));

        // 성공한 경우 DAO저장
        if(timerResponseSuccess.getSuccess())
            jpaTimerRepository.save(timer);

        return timerResponseSuccess;
    }
}
