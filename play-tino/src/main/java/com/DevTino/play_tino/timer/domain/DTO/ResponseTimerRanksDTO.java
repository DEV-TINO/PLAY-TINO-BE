package com.DevTino.play_tino.timer.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ResponseTimerRanksDTO {
    Integer timerTotal;
    List<ResponseTimerReadAllDTO> timerList;
}
