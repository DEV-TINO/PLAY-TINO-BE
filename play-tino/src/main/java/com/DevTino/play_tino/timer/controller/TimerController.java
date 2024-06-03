package com.DevTino.play_tino.timer.controller;


import com.DevTino.play_tino.timer.domain.DTO.ResponseTimerDTO;
import com.DevTino.play_tino.timer.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
//@CrossOrigin(origins = "https://play-tino.com") // 배포
@CrossOrigin("*") // 로컬
@RequestMapping("/timer")
public class TimerController {

    TimerService timerService;

    @Autowired
    public TimerController(TimerService timerService){
        this.timerService = timerService;
    }

    //목표시간 조회 -> 게임 시작 o
    @GetMapping("/start/user/{userId}")
    public ResponseTimerDTO save(@PathVariable("userId") UUID userId, @RequestParam(value = "gameId", required = false) UUID gameId){
        return timerService.getTargetTime(userId, gameId);
    }

}
