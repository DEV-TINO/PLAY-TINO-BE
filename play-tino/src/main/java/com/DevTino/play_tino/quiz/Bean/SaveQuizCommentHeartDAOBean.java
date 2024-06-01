package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizCommentHeart;
import com.DevTino.play_tino.quiz.repository.JpaQuizCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQuizCommentHeartDAOBean {

    JpaQuizCommentHeartRepository jpaQuizCommentHeartRepository;

    @Autowired
    public SaveQuizCommentHeartDAOBean(JpaQuizCommentHeartRepository jpaQuizCommentHeartRepository) {
        this.jpaQuizCommentHeartRepository = jpaQuizCommentHeartRepository;
    }

    //댓글 좋아요 저장
    public void exec(QuizCommentHeart quizCommentHeart) {
        jpaQuizCommentHeartRepository.save(quizCommentHeart);
    }
}
