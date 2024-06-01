package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import org.springframework.stereotype.Component;

@Component
public class CheckVaildTimerCommentBean {
    public Boolean exec(TimerComment timerComment){

        if(timerComment.getCommentId() != null && timerComment.getUserId() != null && timerComment.getContent() != null && timerComment.getHeartCount() != null && timerComment.getUploadTime() != null)
            return true;
        else return false;
    }
}
