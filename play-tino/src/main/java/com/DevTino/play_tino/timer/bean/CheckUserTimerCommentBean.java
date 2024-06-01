package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CheckUserTimerCommentBean {

    public Boolean exec(TimerComment timerComment, UUID commentId){

        return timerComment.getCommentId().equals(commentId);
    }

}
