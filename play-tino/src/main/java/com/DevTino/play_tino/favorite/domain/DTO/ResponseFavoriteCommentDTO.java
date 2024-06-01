package com.DevTino.play_tino.favorite.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
//@Builder
public class ResponseFavoriteCommentDTO {
    UUID commentId;
    UUID userId;
    String content;
    Integer heartCount;
    LocalDateTime uploadTime;
}
