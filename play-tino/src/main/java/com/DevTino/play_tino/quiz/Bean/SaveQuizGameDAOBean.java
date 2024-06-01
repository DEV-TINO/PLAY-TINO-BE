package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizGame;
import com.DevTino.play_tino.quiz.repository.JpaQuizGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQuizGameDAOBean {
    JpaQuizGameRepository jpaQuizGameRepository;

    @Autowired
    public SaveQuizGameDAOBean(JpaQuizGameRepository jpaQuizGameRepository) {
        this.jpaQuizGameRepository = jpaQuizGameRepository;
    }

    //퀴즈 게임 저장
    public QuizGame exec(QuizGame quizGame) {
        return jpaQuizGameRepository.save(quizGame);
    }
}
