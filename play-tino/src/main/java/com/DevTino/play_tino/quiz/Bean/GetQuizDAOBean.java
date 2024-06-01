package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.Quiz;
import com.DevTino.play_tino.quiz.repository.JpaQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetQuizDAOBean {
    JpaQuizRepository jpaQuizRepository;

    @Autowired
    public GetQuizDAOBean(JpaQuizRepository jpaQuizRepository) {
        this.jpaQuizRepository = jpaQuizRepository;
    }

    //퀴즈 랜덤으로 10개 담기
    public List<Quiz> exec() {
        //10개를 담아줄 리스트 선언
        List<Quiz> returnQuizList = new ArrayList<>();

        //상식 5문제 랜덤으로 받기
        List<Quiz> commonsenseList = jpaQuizRepository.findAllByCategory("상식");

        for(int i = 0; i < 5; i++) {
            returnQuizList.add(commonsenseList.get(i));
        }


        //넌센스 5문제 랜덤으로 받기
        List<Quiz> nonsenseList = jpaQuizRepository.findAllByCategory("넌센스");

        for(int i = 0; i < 5; i++) {
            returnQuizList.add(nonsenseList.get(i));
        }

        //10개를 담아준 리스트 반환
        return returnQuizList;
    }
}
