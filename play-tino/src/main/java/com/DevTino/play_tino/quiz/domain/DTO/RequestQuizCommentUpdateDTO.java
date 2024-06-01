package com.DevTino.play_tino.quiz.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestQuizCommentUpdateDTO {
    UUID commentId;
    UUID userId;
    String content;
}
