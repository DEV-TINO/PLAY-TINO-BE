package com.DevTino.play_tino.quiz.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ResponseQuizRanksDTO {
    Integer quizTotal;
    List<ResponseQuizRankDTO> quizRankList;
}
