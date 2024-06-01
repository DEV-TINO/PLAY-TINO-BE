package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizCommentHeart;
import com.DevTino.play_tino.quiz.repository.JpaQuizCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteQuizCommentHeartDAOBean {
    JpaQuizCommentHeartRepository jpaQuizCommentHeartRepository;

    @Autowired
    public DeleteQuizCommentHeartDAOBean(JpaQuizCommentHeartRepository jpaQuizCommentHeartRepository) {
        this.jpaQuizCommentHeartRepository = jpaQuizCommentHeartRepository;
    }

    //댓글 좋아요 삭제
    public void exec(QuizCommentHeart quizCommentHeart) {
        jpaQuizCommentHeartRepository.delete(quizCommentHeart);
    }
}
