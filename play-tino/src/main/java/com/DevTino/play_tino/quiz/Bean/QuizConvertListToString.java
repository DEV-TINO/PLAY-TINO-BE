package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.DTO.ResponseQuizCommentDTO;
import com.DevTino.play_tino.quiz.domain.Quiz;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizConvertListToString {
    public String exec(List<Quiz> QuizList) {

        //ObjectMapper를 이용해 List를 String으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String stringQuizList = "";
        try {
            stringQuizList = objectMapper.writeValueAsString(QuizList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return stringQuizList;
    }

    public String exec(List<ResponseQuizCommentDTO> commentDTOList, int a) {

        //ObjectMapper를 이용해 List를 String으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String stringcommentDTOList = "";
        try {
            stringcommentDTOList = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(commentDTOList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return stringcommentDTOList;
    }

}
