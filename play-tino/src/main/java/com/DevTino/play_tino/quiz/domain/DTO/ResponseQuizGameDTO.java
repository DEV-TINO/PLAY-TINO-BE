package com.DevTino.play_tino.quiz.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseQuizGameDTO {
    UUID gameId;
    UUID userId;

    String responseQuizList;


}
