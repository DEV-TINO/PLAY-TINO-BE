package com.DevTino.play_tino.timer.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseTimerCommentHeartCheckDTO {
    UUID commentId;
    UUID userId;
    String content;
    Integer heartCount;
    String userName;
    LocalDateTime uploadTime;
    Boolean userHeart;
}
