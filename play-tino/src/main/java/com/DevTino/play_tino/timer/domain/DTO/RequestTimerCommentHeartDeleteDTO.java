package com.DevTino.play_tino.timer.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestTimerCommentHeartDeleteDTO {
    UUID userId;
    UUID commentId;
}
