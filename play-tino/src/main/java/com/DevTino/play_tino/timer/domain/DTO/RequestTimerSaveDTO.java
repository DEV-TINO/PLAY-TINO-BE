package com.DevTino.play_tino.timer.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestTimerSaveDTO {
    UUID gameId;
    UUID userId;
    String stopTime;
    String targetTime;
}
