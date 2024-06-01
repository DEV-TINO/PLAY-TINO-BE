package com.DevTino.play_tino.quiz.service;

import com.DevTino.play_tino.quiz.Bean.*;
import com.DevTino.play_tino.quiz.domain.QuizComment;
import com.DevTino.play_tino.quiz.domain.QuizCommentHeart;
import com.DevTino.play_tino.quiz.domain.DTO.RequestQuizCommentHeartDeleteDTO;
import com.DevTino.play_tino.quiz.domain.DTO.RequestQuizCommentHeartSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuizCommentHeartService {

    GetQuizCommentHeartDAOBean getQuizCommentHeartDAOBean;
    GetQuizCommentDAOBean getQuizCommentDAOBean;
    UpdateQuizCommentHeartCountDAOBean updateQuizCommentHeartCountDAOBean;
    SaveQuizCommentHeartDAOBean saveQuizCommentHeartDAOBean;
    SaveQuizCommentDAOBean saveQuizCommentDAOBean;
    DeleteQuizCommentHeartDAOBean deleteQuizCommentHeartDAOBean;

    @Autowired
    public QuizCommentHeartService(GetQuizCommentHeartDAOBean getQuizCommentHeartDAOBean, GetQuizCommentDAOBean getQuizCommentDAOBean, UpdateQuizCommentHeartCountDAOBean updateQuizCommentHeartCountDAOBean, SaveQuizCommentHeartDAOBean saveQuizCommentHeartDAOBean, SaveQuizCommentDAOBean saveQuizCommentDAOBean, DeleteQuizCommentHeartDAOBean deleteQuizCommentHeartDAOBean) {
        this.getQuizCommentHeartDAOBean = getQuizCommentHeartDAOBean;
        this.getQuizCommentDAOBean = getQuizCommentDAOBean;
        this.updateQuizCommentHeartCountDAOBean = updateQuizCommentHeartCountDAOBean;
        this.saveQuizCommentHeartDAOBean = saveQuizCommentHeartDAOBean;
        this.saveQuizCommentDAOBean = saveQuizCommentDAOBean;
        this.deleteQuizCommentHeartDAOBean = deleteQuizCommentHeartDAOBean;
    }

    //댓글창 하트를 저장
    public UUID saveCommentHeart(RequestQuizCommentHeartSaveDTO requestQuizCommentHeartSaveDTO) {

        //이미 좋아요되어있으면 null 반환
        if (getQuizCommentHeartDAOBean.exec(requestQuizCommentHeartSaveDTO.getCommentId(), requestQuizCommentHeartSaveDTO.getUserId()) != null)
            return null;

        //commentHeart 객체 생성
        //DTO를 통해서 DAO를 만들어줌
        QuizCommentHeart quizCommentHeart = new QuizCommentHeart();

        //coommentHeart(DAO)값 초기화
        quizCommentHeart.setCommentId(requestQuizCommentHeartSaveDTO.getCommentId());
        quizCommentHeart.setUserId(requestQuizCommentHeartSaveDTO.getUserId());

        //commentId를 통해 원하는 comment 객체 찾기
        QuizComment quizComment = getQuizCommentDAOBean.exec(requestQuizCommentHeartSaveDTO.getCommentId());
        if (quizComment == null) return null;

        //찾은 comment 객체 좋아요 추가
        updateQuizCommentHeartCountDAOBean.heartCountUp(quizComment);

        //댓글 좋아요를 저장
        saveQuizCommentHeartDAOBean.exec(quizCommentHeart);

        //바뀐 댓글을 저장
        saveQuizCommentDAOBean.exec(quizComment);

        //키값을 반환
        return quizCommentHeart.getCommentHeartId();
    }


    //댓글창 하트를 삭제
    public UUID deleteCommentHeart(RequestQuizCommentHeartDeleteDTO requestQuizCommentHeartDeleteDTO) {
        // commentId와 UserId로 해당 CommentHeart(DAO) 찾기
        QuizCommentHeart quizCommentHeart = getQuizCommentHeartDAOBean.exec(requestQuizCommentHeartDeleteDTO.getCommentId(), requestQuizCommentHeartDeleteDTO.getUserId());

        // 좋아요를 누른적 없으면 null값 반환
        if (quizCommentHeart == null) return null;

        // commentId를 통해 원하는 comment 객체 찾기
        QuizComment quizComment = getQuizCommentDAOBean.exec(requestQuizCommentHeartDeleteDTO.getCommentId());
        if (quizComment == null)    return null;

        // 찾은 comment 객체 좋아요 삭제
        updateQuizCommentHeartCountDAOBean.heartCountDown(quizComment);

        // 댓글 좋아요(DAO)값을 삭제
        deleteQuizCommentHeartDAOBean.exec(quizCommentHeart);

        // 바뀐 댓글을 저장
        saveQuizCommentDAOBean.exec(quizComment);

        // 키값을 반환
        return quizCommentHeart.getCommentHeartId();
    }
}
