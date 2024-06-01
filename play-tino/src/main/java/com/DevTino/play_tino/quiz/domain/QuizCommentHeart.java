package com.DevTino.play_tino.quiz.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizCommentHeart {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    UUID CommentHeartId;
    UUID userId;
    UUID commentId;
}
