package com.DevTino.play_tino.timer.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimerCommentHeart {
    @Id
    UUID commentHeartId;
    UUID userId;
    UUID commentId;
}
