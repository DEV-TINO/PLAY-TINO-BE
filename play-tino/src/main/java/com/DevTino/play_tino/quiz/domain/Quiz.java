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
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    UUID quizId;
    String question;
    String answer;
    //String correct;
    String hint;
    String category;
}
