package com.DevTino.play_tino.favorite.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ResponseFavoriteCommentsDTO {
    Integer commentTotal;

    List<ResponseFavoriteCommentByUserDTO> commentList;
}
