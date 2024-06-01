package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.repository.JpaQuizCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQuizCommentCountDAOBean {
    JpaQuizCommentRepository jpaQuizCommentRepository;

    @Autowired
    public GetQuizCommentCountDAOBean(JpaQuizCommentRepository jpaQuizCommentRepository) {
        this.jpaQuizCommentRepository = jpaQuizCommentRepository;
    }

    public Integer exec() {
        return jpaQuizCommentRepository.findAll().size();
    }
}
