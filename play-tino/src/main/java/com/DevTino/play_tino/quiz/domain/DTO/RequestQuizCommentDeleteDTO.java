package com.DevTino.play_tino.quiz.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestQuizCommentDeleteDTO {
    UUID commentId;
    UUID userId;
}
