package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.repository.JpaQuizRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQuizRankCountDAOBean {
    JpaQuizRankRepository jpaQuizRankRepository;

    @Autowired
    public GetQuizRankCountDAOBean(JpaQuizRankRepository jpaQuizRankRepository) {
        this.jpaQuizRankRepository = jpaQuizRankRepository;
    }

    public Integer exec() {
        return jpaQuizRankRepository.findTop100ByOrderByAllCorrectDesc().size();
    }
}
