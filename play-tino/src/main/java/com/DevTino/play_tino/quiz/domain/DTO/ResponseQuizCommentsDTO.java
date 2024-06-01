package com.DevTino.play_tino.quiz.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ResponseQuizCommentsDTO {
    Integer commentTotal;
    List<ResponseQuizCommentDTO> commentList;
}
