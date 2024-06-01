package com.DevTino.play_tino.favorite.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoriteComment {
    @Id
    UUID commentId;

    UUID userId;
    String content;
    Integer heartCount;
    LocalDateTime uploadTime;
}
