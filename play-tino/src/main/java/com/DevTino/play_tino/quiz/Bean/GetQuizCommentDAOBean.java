package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizComment;
import com.DevTino.play_tino.quiz.repository.JpaQuizCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetQuizCommentDAOBean {
    JpaQuizCommentRepository jpaQuizCommentRepository;

    @Autowired
    public GetQuizCommentDAOBean(JpaQuizCommentRepository jpaQuizCommentRepository) {
        this.jpaQuizCommentRepository = jpaQuizCommentRepository;
    }

    //CommentId와 UserId를 통해 원하는 객체 찾기
    public QuizComment exec(UUID commentId, UUID userId) {
        return jpaQuizCommentRepository.findByCommentIdAndUserId(commentId, userId);
    }

    //UserId를 통해 원하는 객체 찾기
    public QuizComment exec(UUID commentId) {
        return jpaQuizCommentRepository.findById(commentId).get();
    }
}
