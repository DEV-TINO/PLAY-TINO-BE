package com.DevTino.play_tino.timer.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestTimerCommentSaveDTO {
    UUID userId;
    String content;
}
