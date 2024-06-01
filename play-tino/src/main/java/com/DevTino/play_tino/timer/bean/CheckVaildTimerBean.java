package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.Timer;
import org.springframework.stereotype.Component;

@Component
public class CheckVaildTimerBean {

    public boolean exec(Timer timer) {
        if(timer.getGameId() != null && timer.getStopTime() != null && timer.getTargetTime() != null && timer.getUserId() != null && timer.getErrorRange() != null)
            return true;
        return false;
    }
}
