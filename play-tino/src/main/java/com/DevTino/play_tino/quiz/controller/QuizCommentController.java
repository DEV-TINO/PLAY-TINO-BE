package com.DevTino.play_tino.quiz.controller;

import com.DevTino.play_tino.quiz.domain.DTO.RequestQuizCommentDeleteDTO;
import com.DevTino.play_tino.quiz.domain.DTO.RequestQuizCommentSaveDTO;
import com.DevTino.play_tino.quiz.domain.DTO.RequestQuizCommentUpdateDTO;
import com.DevTino.play_tino.quiz.domain.DTO.ResponseQuizCommentsDTO;
import com.DevTino.play_tino.quiz.service.QuizCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://play-tino.com")
@RequestMapping("/quiz")
public class QuizCommentController {

    QuizCommentService quizCommentService;

    @Autowired
    public QuizCommentController(QuizCommentService quizCommentService) {
        this.quizCommentService = quizCommentService;
    }

    //좋아요순으로 전체조회
    @GetMapping("/comment/all/user/{userId}/heart")
    public ResponseQuizCommentsDTO readCommentHeartCount(@PathVariable UUID userId, @RequestParam(required = false, defaultValue="0") int page) {
        return quizCommentService.readCommentHeartCount(userId, page, 10);
    }

    //최신순으로 전체조회
    @GetMapping("/comment/all/user/{userId}/time")
    public ResponseQuizCommentsDTO readCommentUploadTime(@PathVariable UUID userId, @RequestParam(required = false, defaultValue="0") int page) {
        return quizCommentService.readCommentUploadTime(userId, page, 10);
    }

    //댓글 저장
    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody RequestQuizCommentSaveDTO requestQuizCommentSaveDTO) {
        UUID commentId = quizCommentService.createComment(requestQuizCommentSaveDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", commentId != null);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    //댓글 수정
    @PutMapping("/comment")
    public ResponseEntity<Map<String, Object>> updateComment(@RequestBody RequestQuizCommentUpdateDTO requestQuizCommentUpdateDTO) {
        UUID commentId = quizCommentService.updateComment(requestQuizCommentUpdateDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", commentId != null);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    //댓글 삭제
    @DeleteMapping("/comment")
    public ResponseEntity<Map<String, Object>> deleteComment(@RequestBody RequestQuizCommentDeleteDTO requestQuizCommentDeleteDTO) {
        UUID commentId = quizCommentService.deleteComment(requestQuizCommentDeleteDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", commentId != null);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

}
