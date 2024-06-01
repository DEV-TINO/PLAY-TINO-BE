package com.DevTino.play_tino.favorite.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseFavoriteCommentByUserDTO {
    UUID commentId;
    UUID userId;
    String userName;
    String content;
    Integer heartCount;
    LocalDateTime uploadTime;

    Boolean userHeart;
}
