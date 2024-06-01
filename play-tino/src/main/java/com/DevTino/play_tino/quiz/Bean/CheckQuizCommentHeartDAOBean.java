package com.DevTino.play_tino.quiz.Bean;


import com.DevTino.play_tino.quiz.repository.JpaQuizCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CheckQuizCommentHeartDAOBean {
    JpaQuizCommentHeartRepository jpaQuizCommentHeartRepository;

    @Autowired
    public CheckQuizCommentHeartDAOBean(JpaQuizCommentHeartRepository jpaQuizCommentHeartRepository) {
        this.jpaQuizCommentHeartRepository = jpaQuizCommentHeartRepository;
    }

    //commentID와 userId를 통해 유저가 comment 에 좋아요를 눌렀는지 boolean 값으로 반환
    public boolean checkMyHeart(UUID commentId, UUID userId) {
        return jpaQuizCommentHeartRepository.existsByCommentIdAndUserId(commentId, userId);
    }
}
