package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizGame;
import com.DevTino.play_tino.quiz.repository.JpaQuizGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetQuizGameDAOBean {
    JpaQuizGameRepository jpaQuizGameRepository;

    @Autowired
    public GetQuizGameDAOBean(JpaQuizGameRepository jpaQuizGameRepository) {
        this.jpaQuizGameRepository = jpaQuizGameRepository;
    }

    //GameId와 UserID를 통해 원하는 QuizGame객체 찾기
    public QuizGame exec(UUID gameId, UUID userId) {
        return jpaQuizGameRepository.findByGameIdAndUserId(gameId, userId);
    }
}
