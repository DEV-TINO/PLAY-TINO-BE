package com.DevTino.play_tino.quiz.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseQuizDTO {
    UUID quizId;
    String question;
    String correct;
    String answer;
    String hint;
    String category;
}
