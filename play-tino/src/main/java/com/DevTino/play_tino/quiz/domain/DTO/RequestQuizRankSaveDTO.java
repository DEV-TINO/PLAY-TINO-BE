package com.DevTino.play_tino.quiz.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestQuizRankSaveDTO {
    UUID gameId;
    UUID userId;
    Integer nonsenseCorrect;
    Integer commonsenseCorrect;
    boolean top100Ranking;
}
