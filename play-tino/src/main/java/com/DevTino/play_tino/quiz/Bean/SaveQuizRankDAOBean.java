package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizRank;
import com.DevTino.play_tino.quiz.repository.JpaQuizRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQuizRankDAOBean {
    JpaQuizRankRepository jpaQuizRankRepository;

    @Autowired
    public SaveQuizRankDAOBean(JpaQuizRankRepository jpaQuizRankRepository) {
        this.jpaQuizRankRepository = jpaQuizRankRepository;
    }

    //퀴즈 랭크 저장
    public QuizRank exec(QuizRank quizRank) {
        return jpaQuizRankRepository.save(quizRank);
    }
}
