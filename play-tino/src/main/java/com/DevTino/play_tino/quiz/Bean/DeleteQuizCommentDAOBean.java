package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizComment;
import com.DevTino.play_tino.quiz.repository.JpaQuizCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteQuizCommentDAOBean {
    JpaQuizCommentRepository jpaQuizCommentRepository;

    @Autowired
    public DeleteQuizCommentDAOBean(JpaQuizCommentRepository jpaQuizCommentRepository) {
        this.jpaQuizCommentRepository = jpaQuizCommentRepository;
    }

    //댓글 삭제
    public void exec(QuizComment quizComment) {
        jpaQuizCommentRepository.delete(quizComment);
    }
}
