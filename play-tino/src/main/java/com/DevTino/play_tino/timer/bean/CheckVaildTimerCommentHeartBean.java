package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerCommentHeart;
import org.springframework.stereotype.Component;

@Component
public class CheckVaildTimerCommentHeartBean {
    public Boolean exec(TimerCommentHeart timerCommentHeart){
        if (timerCommentHeart.getCommentHeartId() != null && timerCommentHeart.getCommentId() != null && timerCommentHeart.getUserId() !=null)
            return true;

        else return false;
    }
}
