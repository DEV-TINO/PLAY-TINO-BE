package com.DevTino.play_tino.favorite.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestFavoriteCommentSaveDTO {
    UUID userId;
    String content;
}
