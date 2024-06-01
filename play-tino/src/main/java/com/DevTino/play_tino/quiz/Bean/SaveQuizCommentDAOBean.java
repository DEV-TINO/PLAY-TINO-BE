package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizComment;
import com.DevTino.play_tino.quiz.repository.JpaQuizCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQuizCommentDAOBean {

    JpaQuizCommentRepository jpaQuizCommentRepository;

    @Autowired
    public SaveQuizCommentDAOBean(JpaQuizCommentRepository jpaQuizCommentRepository){
        this.jpaQuizCommentRepository = jpaQuizCommentRepository;
    }

    // 댓글 저장
    public void exec(QuizComment quizComment){
        jpaQuizCommentRepository.save(quizComment);
    }
}