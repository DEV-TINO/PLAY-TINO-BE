package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizRank;
import com.DevTino.play_tino.quiz.repository.JpaQuizRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckTop100QuizRankingDAOBean {
    JpaQuizRankRepository jpaQuizRankRepository;

    @Autowired
    public CheckTop100QuizRankingDAOBean(JpaQuizRankRepository jpaQuizRankRepository) {
        this.jpaQuizRankRepository = jpaQuizRankRepository;
    }

    public List<QuizRank> exec() {
        return jpaQuizRankRepository.findTop100ByOrderByAllCorrectDescUpdateAtDesc();
    }
}
