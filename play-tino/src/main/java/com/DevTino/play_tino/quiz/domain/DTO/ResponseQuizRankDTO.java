package com.DevTino.play_tino.quiz.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseQuizRankDTO {
    UUID quizRankId;
    Integer nonsenseCorrect;
    Integer commonsenseCorrect;
    Integer allCorrect;
    String userName;
}

