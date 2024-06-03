package com.DevTino.play_tino.timer.controller;

import com.DevTino.play_tino.timer.domain.DTO.RequestTimerSaveDTO;
import com.DevTino.play_tino.timer.domain.DTO.ResponseSaveTimerRankDTO;
import com.DevTino.play_tino.timer.domain.DTO.ResponseTimerRanksDTO;
import com.DevTino.play_tino.timer.service.TimerRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "https://play-tino.com") // 배포
@CrossOrigin("*") // 로컬
@RequestMapping("/timer")
public class TimerRankController {

    TimerRankService timerRankService;

    @Autowired
    public TimerRankController(TimerRankService timerRankService){
        this.timerRankService = timerRankService;
    }

    // 랭킹 전체 조회 o
    @GetMapping("/rank/all")
    public ResponseTimerRanksDTO readAll(@RequestParam (required = false) Integer page){
        Pageable pageable = PageRequest.of(page, 5
        );
        return timerRankService.readAll(pageable);
    }

    // 타이머 랭킹 저장 o
    @PostMapping("/rank")
    public ResponseSaveTimerRankDTO save(@RequestBody RequestTimerSaveDTO requestTimerSaveDTO){

        ResponseSaveTimerRankDTO responseSaveTimerRankDTO = timerRankService.save(requestTimerSaveDTO);

        if (responseSaveTimerRankDTO == null){
            ResponseSaveTimerRankDTO responseSaveTimerRankDTO1 = new ResponseSaveTimerRankDTO();
            responseSaveTimerRankDTO1.setSuccess(false);
            return responseSaveTimerRankDTO1;
        }

        else
            return responseSaveTimerRankDTO;
    }
}
