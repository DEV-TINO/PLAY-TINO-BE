package com.DevTino.play_tino.timer.service;

import com.DevTino.play_tino.timer.bean.*;
import com.DevTino.play_tino.timer.domain.DTO.RequestTimerCommentHeartDeleteDTO;
import com.DevTino.play_tino.timer.domain.DTO.RequestTimerCommentHeartSaveDTO;
import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import com.DevTino.play_tino.timer.domain.entity.TimerCommentHeart;
import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentHeartService {

    GetTimerCommentHeartDAOBean getTimerCommentHeartDAOBean;
    UpdateTimerCommentHeartDAOBean updateTimerCommentHeartDAOBean;
    SaveTimerCommentDAOBean saveTimerCommentDAOBean;
    SaveTimerCommentHeartDAOBean saveTimerCommentHeartDAOBean;
    DeleteTimerCommentHeartDAOBean deleteTimerCommentHeartDAOBean;
    GetTimerCommentDAOBean getTimerCommentDAOBean;

    @Autowired
    public CommentHeartService(GetTimerCommentHeartDAOBean getTimerCommentHeartDAOBean, UpdateTimerCommentHeartDAOBean updateTimerCommentHeartDAOBean, SaveTimerCommentDAOBean saveTimerCommentDAOBean, SaveTimerCommentHeartDAOBean saveTimerCommentHeartDAOBean, DeleteTimerCommentHeartDAOBean deleteTimerCommentHeartDAOBean, GetTimerCommentDAOBean getTimerCommentDAOBean) {
        this.getTimerCommentHeartDAOBean = getTimerCommentHeartDAOBean;
        this.updateTimerCommentHeartDAOBean = updateTimerCommentHeartDAOBean;
        this.saveTimerCommentDAOBean = saveTimerCommentDAOBean;
        this.saveTimerCommentHeartDAOBean = saveTimerCommentHeartDAOBean;
        this.deleteTimerCommentHeartDAOBean = deleteTimerCommentHeartDAOBean;
        this.getTimerCommentDAOBean = getTimerCommentDAOBean;
    }

    //댓글 하트 생성
    public TimerResponseSuccess save(RequestTimerCommentHeartSaveDTO requestTimerCommentHeartSaveDTO){

        // 좋아요 있는지 확인
        if (getTimerCommentHeartDAOBean.exec(requestTimerCommentHeartSaveDTO.getCommentId(), requestTimerCommentHeartSaveDTO.getUserId()) != null){
            // 좋아요가 있으면 그대로 반환
            TimerResponseSuccess timerResponseSuccess = new TimerResponseSuccess();
            timerResponseSuccess.setSuccess(false);
            return timerResponseSuccess;
        }

        // 저장할 좋아요 DAO 생성 및 초기화
        TimerCommentHeart timerCommentHeart = new TimerCommentHeart();

        timerCommentHeart.setCommentHeartId(UUID.randomUUID());
        timerCommentHeart.setUserId(requestTimerCommentHeartSaveDTO.getUserId());
        timerCommentHeart.setCommentId(requestTimerCommentHeartSaveDTO.getCommentId());

        // 좋아요 갯수 증가
        TimerComment timerComment = updateTimerCommentHeartDAOBean.commentHeartUp(requestTimerCommentHeartSaveDTO.getCommentId());

        // 좋아요 수 변경된 댓글 저장
        saveTimerCommentDAOBean.exec(timerComment);

        // 좋아요 DAO 저장
        return saveTimerCommentHeartDAOBean.exec(timerCommentHeart);
    }

    // 댓글 하트 삭제
    public TimerResponseSuccess delete(RequestTimerCommentHeartDeleteDTO requestTimerCommentHeartDeleteDTO){

        // DAO 가져오기
        TimerCommentHeart timerCommentHeart = getTimerCommentHeartDAOBean.exec(requestTimerCommentHeartDeleteDTO.getCommentId(), requestTimerCommentHeartDeleteDTO.getUserId());

        // 좋아요가 없으면 false 반환
        if(timerCommentHeart == null) {
            TimerResponseSuccess timerResponseSuccess = new TimerResponseSuccess();
            timerResponseSuccess.setSuccess(false);
            return timerResponseSuccess;
        }

        // 좋아요 갯수 감소
        TimerComment timerComment = getTimerCommentDAOBean.exec(requestTimerCommentHeartDeleteDTO.getCommentId());
        updateTimerCommentHeartDAOBean.commentHeartDown(timerComment);

        // 좋아요 수 변경된 댓글 저장
        saveTimerCommentDAOBean.exec(timerComment);

        // 좋아요 DAO 저장
        return deleteTimerCommentHeartDAOBean.exec(timerCommentHeart);
    }

}
