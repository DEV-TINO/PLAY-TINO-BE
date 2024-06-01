package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizComment;
import com.DevTino.play_tino.quiz.repository.JpaQuizCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateQuizCommentHeartCountDAOBean {

    JpaQuizCommentRepository jpaQuizCommentRepository;

    @Autowired
    public UpdateQuizCommentHeartCountDAOBean(JpaQuizCommentRepository jpaQuizCommentRepository){
        this.jpaQuizCommentRepository = jpaQuizCommentRepository;
    }

    //댓글좋아요 추가(+1)
    public QuizComment heartCountUp(QuizComment quizComment){
        //DAO의 댓글좋아요개수(HeartCount) +1
        quizComment.setHeartCount(quizComment.getHeartCount()+1);

        //comment 객체 반환
        return quizComment;

    }

    //댓글좋아요 삭제(-1)
    public QuizComment heartCountDown(QuizComment quizComment){
        //댓글좋아요개수(HeartCount) -1
        quizComment.setHeartCount(quizComment.getHeartCount()-1);

        //comment 객체 반환
        return quizComment;

    }

}
