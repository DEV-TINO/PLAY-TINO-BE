package com.DevTino.play_tino.timer.service;

import com.DevTino.play_tino.timer.bean.*;
import com.DevTino.play_tino.timer.domain.DTO.*;
import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import com.DevTino.play_tino.user.Bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    SaveTimerCommentDAOBean saveTimerCommentDAOBean;
    UpdateTimerCommentDAOBean updateTimerCommentDAOBean;
    GetTimerCommentDAOBean getTimerCommentDAOBean;
    GetTimerCommentDAOsBean getTimerCommentDAOsBean;
    DeleteTimerCommentDAOBean deleteTimerCommentDAOBean;
    GetUserTimerHeartBean getUserTimerHeartBean;
    GetTimerCommentTotalDAOBean getTimerCommentTotalDAOBean;
    GetUserDAOBean getUserDAOBean;

    @Autowired
    public CommentService(SaveTimerCommentDAOBean saveTimerCommentDAOBean, UpdateTimerCommentDAOBean updateTimerCommentDAOBean, GetTimerCommentDAOBean getTimerCommentDAOBean, GetTimerCommentDAOsBean getTimerCommentDAOsBean, DeleteTimerCommentDAOBean deleteTimerCommentDAOBean, GetUserTimerHeartBean getUserTimerHeartBean, GetTimerCommentTotalDAOBean getTimerCommentTotalDAOBean, GetUserDAOBean getUserDAOBean) {
        this.saveTimerCommentDAOBean = saveTimerCommentDAOBean;
        this.updateTimerCommentDAOBean = updateTimerCommentDAOBean;
        this.getTimerCommentDAOBean = getTimerCommentDAOBean;
        this.getTimerCommentDAOsBean = getTimerCommentDAOsBean;
        this.deleteTimerCommentDAOBean = deleteTimerCommentDAOBean;
        this.getUserTimerHeartBean = getUserTimerHeartBean;
        this.getTimerCommentTotalDAOBean = getTimerCommentTotalDAOBean;
        this.getUserDAOBean = getUserDAOBean;
    }

    // 댓글 저장
    public TimerResponseSuccess save(RequestTimerCommentSaveDTO requestTimerCommentSaveDTO) {

        // DTO를 통해서 DAO를 만들어줌
        TimerComment timerComment = new TimerComment();

        timerComment.setCommentId(UUID.randomUUID());
        timerComment.setHeartCount(0);
        timerComment.setUploadTime(LocalDateTime.now());

        // DAO 값 초기화
        timerComment.setUserId(requestTimerCommentSaveDTO.getUserId());
        timerComment.setContent(requestTimerCommentSaveDTO.getContent());

        return saveTimerCommentDAOBean.exec(timerComment);
    }

    // 댓글 1개 조회 
    public ResponseTimerCommentDTO read(UUID commentId) {

        //id를 통해서 comment 객체를 찾아
        // findById -> PK값을 통해서 해당 객체를 찾아서 반환해줌
        TimerComment timerComment = getTimerCommentDAOBean.exec(commentId);

        // 찾은 객체는 DAO임
        // DAO -> DTO로 변환하는 과정
        ResponseTimerCommentDTO responseTimerCommentDTO = new ResponseTimerCommentDTO();

        // DTO객체 값을 초기화
        responseTimerCommentDTO.setCommentId(timerComment.getCommentId());
        responseTimerCommentDTO.setUserId(timerComment.getUserId());
        responseTimerCommentDTO.setContent(timerComment.getContent());
        responseTimerCommentDTO.setHeartCount(timerComment.getHeartCount());
        responseTimerCommentDTO.setUploadTime(timerComment.getUploadTime());

        // 생성된 DTO를 반환
        return responseTimerCommentDTO;
    }

    // 전체 조회
    public ResponseTimerCommentsDTO readAll(String type, UUID userId, Pageable pageable) {

        Page<TimerComment> commentPage = getTimerCommentDAOsBean.exec(pageable);

        // DTO 생성
        List<ResponseTimerCommentHeartCheckDTO> responseCommentCheckDTOList = new ArrayList<>();

        // DAO를 이용해 DTO 초기화
        for (TimerComment timerComment : commentPage) {
            ResponseTimerCommentHeartCheckDTO responseCommentDTO = new ResponseTimerCommentHeartCheckDTO();

            responseCommentDTO.setCommentId(timerComment.getCommentId());
            responseCommentDTO.setUserId(timerComment.getUserId());
            responseCommentDTO.setUserName(getUserDAOBean.exec(timerComment.getUserId()).getUserName());
            responseCommentDTO.setContent(timerComment.getContent());
            responseCommentDTO.setHeartCount(timerComment.getHeartCount());
            responseCommentDTO.setUploadTime(timerComment.getUploadTime());

            // 하트 눌렀는지 여부
            Boolean userHeart = getUserTimerHeartBean.checkUserHeart(timerComment.getCommentId(), userId);
            responseCommentDTO.setUserHeart(userHeart);

            // list에 추가
            responseCommentCheckDTOList.add(responseCommentDTO);
        }

        ResponseTimerCommentsDTO responseTimerCommentsDTO = new ResponseTimerCommentsDTO();
        responseTimerCommentsDTO.setCommentList(responseCommentCheckDTOList);

        responseTimerCommentsDTO.setCommentTotal(getTimerCommentTotalDAOBean.exec());

        // DTO 반환
        return responseTimerCommentsDTO;
    }

    //댓글 수정
    public TimerResponseSuccess update(RequestTimerCommentUpdateDTO requestTimerCommentUpdateDTO) {

        // comment 객체 생성 및 초기화
        TimerComment timerComment = getTimerCommentDAOBean.exec(requestTimerCommentUpdateDTO.getCommentId());

        // 생성한 객체 반환
        return updateTimerCommentDAOBean.exec(timerComment, requestTimerCommentUpdateDTO);
    }

    //댓글 삭제
    public TimerResponseSuccess delete(RequestTimerCommentDeleteDTO requestTimerCommentDeleteDTO) {

        // comment 객체 생성 후 comment 초기화
        TimerComment timerComment = getTimerCommentDAOBean.exec(requestTimerCommentDeleteDTO.getCommentId());

        // 생성한 객체 반환
        return deleteTimerCommentDAOBean.exec(timerComment, requestTimerCommentDeleteDTO);
    }
}