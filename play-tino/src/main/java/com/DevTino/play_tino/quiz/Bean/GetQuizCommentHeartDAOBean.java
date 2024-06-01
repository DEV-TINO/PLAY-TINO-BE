package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizCommentHeart;
import com.DevTino.play_tino.quiz.repository.JpaQuizCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetQuizCommentHeartDAOBean {
    JpaQuizCommentHeartRepository jpaQuizCommentHeartRepository;

    @Autowired
    public GetQuizCommentHeartDAOBean(JpaQuizCommentHeartRepository jpaQuizCommentHeartRepository) {
        this.jpaQuizCommentHeartRepository = jpaQuizCommentHeartRepository;
    }

    //CommentId와 UserId를 통해 원하는 CommentHeart 객체 찾기
    public QuizCommentHeart exec(UUID commentId, UUID userId) {
        return jpaQuizCommentHeartRepository.findByCommentIdAndUserId(commentId, userId);
    }
}
