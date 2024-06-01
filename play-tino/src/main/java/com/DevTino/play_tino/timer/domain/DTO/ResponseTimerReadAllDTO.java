package com.DevTino.play_tino.timer.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseTimerReadAllDTO {
    UUID timerRankId;
    UUID userId;
    String stopTime;
    String TargetTime;
    String errorRange;
    String userName;
    LocalDateTime createTime;
    LocalDateTime uploadTime;
}
