package com.DevTino.play_tino.quiz.controller;

import com.DevTino.play_tino.quiz.domain.DTO.RequestQuizCommentHeartDeleteDTO;
import com.DevTino.play_tino.quiz.domain.DTO.RequestQuizCommentHeartSaveDTO;
import com.DevTino.play_tino.quiz.service.QuizCommentHeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://play-tino.com") // 배포
//@CrossOrigin("*") // 로컬
@RequestMapping("/quiz")
public class QuizCommentHeartController {

    QuizCommentHeartService quizCommentHeartService;

    @Autowired
    public QuizCommentHeartController(QuizCommentHeartService quizCommentHeartService) {
        this.quizCommentHeartService = quizCommentHeartService;
    }

    //댓글 좋아요 추가
    @PostMapping("/comment-heart")
    public ResponseEntity<Map<String, Object>> saveCommentHeart(@RequestBody RequestQuizCommentHeartSaveDTO requestQuizCommentHeartSaveDTO) {
        UUID commentHeartId = quizCommentHeartService.saveCommentHeart(requestQuizCommentHeartSaveDTO);

        HttpStatus httpStatus = (commentHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", commentHeartId != null);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    //댓글 좋아요 삭제
    @DeleteMapping("/comment-heart")
    public ResponseEntity<Map<String, Object>> deleteCommentHeart(@RequestBody RequestQuizCommentHeartDeleteDTO requestQuizCommentHeartDeleteDTO) {
        UUID commentHeartId = quizCommentHeartService.deleteCommentHeart(requestQuizCommentHeartDeleteDTO);

        HttpStatus httpStatus = (commentHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", commentHeartId != null);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
