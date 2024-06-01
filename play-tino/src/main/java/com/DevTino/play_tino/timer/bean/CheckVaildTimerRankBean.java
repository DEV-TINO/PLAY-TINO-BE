package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerRank;
import com.DevTino.play_tino.timer.repository.JpaTimerRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckVaildTimerRankBean {
    JpaTimerRankRepository jpaTimerRankRepository;

    @Autowired
    public CheckVaildTimerRankBean(JpaTimerRankRepository jpaTimerRankRepository){
        this.jpaTimerRankRepository = jpaTimerRankRepository;
    }

    public boolean exec(TimerRank timerRank){
        if(timerRank.getTimerRankId() != null && timerRank.getTimerId() != null && timerRank.getUserId() != null)
            return true;
        return false;
    }
}
