package com.DevTino.play_tino.quiz.controller;

import com.DevTino.play_tino.quiz.domain.DTO.RequestQuizRankSaveDTO;
import com.DevTino.play_tino.quiz.domain.DTO.ResponseQuizGameDTO;
import com.DevTino.play_tino.quiz.domain.DTO.ResponseQuizRanksDTO;
import com.DevTino.play_tino.quiz.service.QuizService;
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
public class QuizController {
    QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    //퀴즈 받아오기(게임 들어갈 시)
    @GetMapping("/start/user/{userId}")
    public ResponseQuizGameDTO readQuizAll(@PathVariable UUID userId, @RequestParam(required = false) UUID gameId) {
        return quizService.readQuiz(gameId, userId);
    }

    //랭킹 전체 조회
    @GetMapping("/rank/all")
    public ResponseQuizRanksDTO readQuizRank(@RequestParam(required = false, defaultValue="0") int page) {
        return quizService.readQuizRank(page, 5);
    }

    //랭킹 저장하기
    @PostMapping("/rank")
    public ResponseEntity<Map<String, Object>> saveQuizRank(@RequestBody RequestQuizRankSaveDTO requestQuizRankDTO) {
        Map<String, String> map = quizService.saveQuizRank(requestQuizRankDTO);

        //UUID gameId = quizService.saveQuizRank(requestQuizRankDTO);
        UUID gameId = UUID.fromString(map.get("gameId"));
        boolean rankIn = Boolean.parseBoolean(map.get("rankIn"));

        HttpStatus httpStatus = (gameId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", gameId != null);
        requestMap.put("rankIn", rankIn);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

}
