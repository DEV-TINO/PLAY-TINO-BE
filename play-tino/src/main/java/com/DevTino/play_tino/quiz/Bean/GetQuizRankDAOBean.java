package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizRank;
import com.DevTino.play_tino.quiz.repository.JpaQuizRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetQuizRankDAOBean {
    JpaQuizRankRepository jpaQuizRankRepository;

    @Autowired
    public GetQuizRankDAOBean(JpaQuizRankRepository jpaQuizRankRepository) {
        this.jpaQuizRankRepository = jpaQuizRankRepository;
    }

    //GameId와 UserId를 통해 원하는 QuizRank 객체 찾기
    public QuizRank exec(UUID gameId, UUID userId) {
        return jpaQuizRankRepository.findByGameIdAndUserId(gameId, userId);
    }
}
