package com.DevTino.play_tino.favorite.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestFavoriteCommentUpdateDTO {
    UUID commentId;
    UUID userId;
    String content;
}
