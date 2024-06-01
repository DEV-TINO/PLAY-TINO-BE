package com.DevTino.play_tino.quiz.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseQuizCommentDTO {
    UUID commentId;
    UUID userId;
    String content;
    String userName;
    Integer heartCount;
    LocalDateTime uploadTime;
    Boolean userHeart;
}
