package com.DevTino.play_tino.timer.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ResponseTimerCommentsDTO {
    Integer commentTotal;
    List<ResponseTimerCommentHeartCheckDTO> commentList;
}
