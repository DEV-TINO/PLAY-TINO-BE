package com.DevTino.play_tino.quiz.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class QuizGame {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    UUID gameId;
    UUID userId;
    String quizList;
    LocalDateTime createAt;
}
