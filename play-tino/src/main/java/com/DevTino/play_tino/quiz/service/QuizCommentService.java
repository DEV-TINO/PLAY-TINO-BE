package com.DevTino.play_tino.quiz.service;

import com.DevTino.play_tino.quiz.Bean.*;
import com.DevTino.play_tino.quiz.domain.QuizComment;
import com.DevTino.play_tino.quiz.domain.DTO.*;
import com.DevTino.play_tino.user.Bean.small.GetUserDAOBean;
import com.DevTino.play_tino.user.Domain.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class QuizCommentService {

    SaveQuizCommentDAOBean saveQuizCommentDAOBean;
    DeleteQuizCommentDAOBean deleteQuizCommentDAOBean;
    CheckQuizCommentHeartDAOBean checkQuizCommentHeartDAOBean;
    GetQuizCommentDAOBean getQuizCommentDAOBean;
    GetQuizCommentsDAOBean getQuizCommentsDAOBean;
    GetQuizCommentCountDAOBean getQuizCommentCountDAOBean;
    GetUserDAOBean getUserDAOBean;

    @Autowired
    public QuizCommentService(SaveQuizCommentDAOBean saveQuizCommentDAOBean, DeleteQuizCommentDAOBean deleteQuizCommentDAOBean, CheckQuizCommentHeartDAOBean checkQuizCommentHeartDAOBean, GetQuizCommentDAOBean getQuizCommentDAOBean, GetQuizCommentsDAOBean getQuizCommentsDAOBean, GetQuizCommentCountDAOBean getQuizCommentCountDAOBean, GetUserDAOBean getUserDAOBean) {
        this.saveQuizCommentDAOBean = saveQuizCommentDAOBean;
        this.deleteQuizCommentDAOBean = deleteQuizCommentDAOBean;
        this.checkQuizCommentHeartDAOBean = checkQuizCommentHeartDAOBean;
        this.getQuizCommentDAOBean = getQuizCommentDAOBean;
        this.getQuizCommentsDAOBean = getQuizCommentsDAOBean;
        this.getQuizCommentCountDAOBean = getQuizCommentCountDAOBean;
        this.getUserDAOBean = getUserDAOBean;
    }

    //인기순 전체조회
    public ResponseQuizCommentsDTO readCommentHeartCount(UUID userId, int page, int size) {
        //하트 갯수에 따라 내림차순으로 찾아주고 댓글 10개씩 페이징 처리해 comments 리스트에 저장
        Pageable pageable = PageRequest.of(page, size);
        Page<QuizComment> comments = getQuizCommentsDAOBean.execHeartCount(pageable);

        // DAO 객체들을 저장할 DTO 리스트 생성
        List<ResponseQuizCommentDTO> responseQuizCommentDTOS = new ArrayList<>();

        //각 리스트 객체마다 DTO를 초기화 시켜준다.
        for(QuizComment quizComment : comments) {
            //DTO 객체 생성
            ResponseQuizCommentDTO responseQuizCommentDTO = new ResponseQuizCommentDTO();

            //comment의 userId를 통해 원하는 유저 찾기
            UserDAO userDAO = getUserDAOBean.exec(quizComment.getUserId());

            //DTO 객체 초기화
            responseQuizCommentDTO.setCommentId(quizComment.getCommentId());
            responseQuizCommentDTO.setUserId(quizComment.getUserId());
            responseQuizCommentDTO.setHeartCount(quizComment.getHeartCount());
            responseQuizCommentDTO.setUploadTime(quizComment.getUploadTime());
            responseQuizCommentDTO.setContent(quizComment.getContent());

            //찾은 유저의 닉네임 설정
            responseQuizCommentDTO.setUserName(userDAO.getUserName());

            //자신이 좋아요를 눌렀던 댓글은 true, 누르지 않았던 댓글은 false를 반환해주는 boolean값
            responseQuizCommentDTO.setUserHeart(checkQuizCommentHeartDAOBean.checkMyHeart(quizComment.getCommentId(), userId));

            //DTO 리스트에 각 DTO 객체 저장
            responseQuizCommentDTOS.add(responseQuizCommentDTO);
        }

        //10개씩 묶은 데이터를 String으로 변환한 후에 DTO에 담아준다
        ResponseQuizCommentsDTO responseQuizCommentsDTO = new ResponseQuizCommentsDTO();
        responseQuizCommentsDTO.setCommentList(responseQuizCommentDTOS);

        //전체 댓글 수 계산
        responseQuizCommentsDTO.setCommentTotal(getQuizCommentCountDAOBean.exec());

        //DTO 리스트 반환
        return responseQuizCommentsDTO;
    }

    //최신순 전체조회
    public ResponseQuizCommentsDTO readCommentUploadTime(UUID userId, int page, int size) {
        //업로드 날짜에 따라 내림차순으로 찾아주고 댓글 10개씩 페이징 처리해 comments 리스트에 저장
        Pageable pageable = PageRequest.of(page, size);
        Page<QuizComment> comments = getQuizCommentsDAOBean.execUpLoadTime(pageable);

        //DAO -> DTO
        List<ResponseQuizCommentDTO> responseQuizCommentDTOS = new ArrayList<>();

        //각 리스트 객체마다 DTO를 초기화 시켜준다.
        for(QuizComment quizComment : comments) {
            //DTO 객체 생성
            ResponseQuizCommentDTO responseQuizCommentDTO = new ResponseQuizCommentDTO();

            //comment의 userId를 통해 원하는 유저 찾기
            UserDAO userDAO = getUserDAOBean.exec(quizComment.getUserId());

            //DTO 객체 초기화
            responseQuizCommentDTO.setCommentId(quizComment.getCommentId());
            responseQuizCommentDTO.setUserId(quizComment.getUserId());
            responseQuizCommentDTO.setHeartCount(quizComment.getHeartCount());
            responseQuizCommentDTO.setUploadTime(quizComment.getUploadTime());
            responseQuizCommentDTO.setContent(quizComment.getContent());

            //찾은 유저의 닉네임 설정
            responseQuizCommentDTO.setUserName(userDAO.getUserName());

            //자신이 좋아요를 눌렀던 댓글은 true, 누르지 않았던 댓글은 false를 반환해주는 boolean값
            responseQuizCommentDTO.setUserHeart(checkQuizCommentHeartDAOBean.checkMyHeart(quizComment.getCommentId(), userId));

            //각 DTO 객체를 DTO 리스트에 저장
            responseQuizCommentDTOS.add(responseQuizCommentDTO);
        }

        //댓글 수와 페이지 단위로 묶인 10개 데이터를 담아줄 DTO 생성
        ResponseQuizCommentsDTO responseQuizCommentsDTO = new ResponseQuizCommentsDTO();
        //전체 댓글 수 계산
        responseQuizCommentsDTO.setCommentTotal(getQuizCommentCountDAOBean.exec());
        //페이지 단위로 묶인 10개의 데이터
        responseQuizCommentsDTO.setCommentList(responseQuizCommentDTOS);

        //DTO 리스트 반환
        return responseQuizCommentsDTO;
    }

    //댓글 생성
    public UUID createComment(RequestQuizCommentSaveDTO requestQuizCommentSaveDTO) {
        // 댓글을 생성하는 로직을 작성
        // DTO를 통해서 DAO를 만들어줌
        QuizComment quizComment = new QuizComment();

        // DAO 값 초기화
        quizComment.setUserId(requestQuizCommentSaveDTO.getUserId());
        quizComment.setContent(requestQuizCommentSaveDTO.getContent());
        quizComment.setHeartCount(0);
        //quizComment.setUploadTime(LocalDateTime.now());
        quizComment.setUploadTime(LocalDateTime.now().plusHours(9));

        // DAO를 저장 -> comment createComment
        saveQuizCommentDAOBean.exec(quizComment);

        // 만들어진 Comment에 PK값을 반환해준다.
        return quizComment.getCommentId();
    }

    //댓글 수정
    public UUID updateComment(RequestQuizCommentUpdateDTO requestQuizCommentUpdateDTO) {
        //commentId와 userId 통해서 원하는 comment 객체를 찾는다
        QuizComment quizComment = getQuizCommentDAOBean.exec(requestQuizCommentUpdateDTO.getCommentId(), requestQuizCommentUpdateDTO.getUserId());

        //comment(DAO)객체 안에 requestCommentUpdateDTO 의 값을 넣어준다
        quizComment.setUserId(requestQuizCommentUpdateDTO.getUserId());
        quizComment.setCommentId(requestQuizCommentUpdateDTO.getCommentId());
        quizComment.setContent(requestQuizCommentUpdateDTO.getContent());

        //DAO를 저장
        saveQuizCommentDAOBean.exec(quizComment);

        //DAO의 키값을 반환
        return quizComment.getCommentId();
    }

    //댓글 삭제
    public UUID deleteComment(RequestQuizCommentDeleteDTO requestQuizCommentDeleteDTO) {
        //commentId와 userId 통해서 원하는 comment 객체를 찾는다
        QuizComment quizComment = getQuizCommentDAOBean.exec(requestQuizCommentDeleteDTO.getCommentId(), requestQuizCommentDeleteDTO.getUserId());

        //댓글(DAO)을 삭제
        deleteQuizCommentDAOBean.exec(quizComment);

        //DAO의 키값을 반환
        return quizComment.getCommentId();
    }

}
