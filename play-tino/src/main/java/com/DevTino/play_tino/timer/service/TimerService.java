package com.DevTino.play_tino.timer.service;

import com.DevTino.play_tino.timer.bean.SaveTargetTimerBean;
import com.DevTino.play_tino.timer.domain.DTO.ResponseTimerDTO;
import com.DevTino.play_tino.timer.domain.entity.Timer;
import com.DevTino.play_tino.timer.repository.JpaTimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TimerService {

    JpaTimerRepository jpaTimerRepository;
    SaveTargetTimerBean saveTargetTimerBean;

    @Autowired
    public TimerService(JpaTimerRepository jpaTimerRepository, SaveTargetTimerBean saveTargetTimerBean){
        this.jpaTimerRepository = jpaTimerRepository;
        this.saveTargetTimerBean = saveTargetTimerBean;
    }

    // targetTime 생성
    public ResponseTimerDTO getTargetTime(UUID userId, UUID gameId){

        // responseTimerDTO 객체 생성
        ResponseTimerDTO responseTimerDTO = new ResponseTimerDTO();

        // 게임을 나갔다 다시 들어온 상황
        if(gameId != null) {
            // 게임 아이디와 유저 아이디로 있는지 판단
            Timer timer = jpaTimerRepository.findByGameIdAndUserId(gameId, userId);

            if (timer == null) return null;

            // 게임에 다시 들어와 timerRepository에 값이 남아 있을 경우
            responseTimerDTO.setUserId(timer.getUserId());
            responseTimerDTO.setGameId(timer.getGameId());
            responseTimerDTO.setTargetTime(timer.getTargetTime());
        // 새로운 게임 생성
        } else {

            // DAO 생성
            Timer timer = new Timer();
            UUID newGameId = UUID.randomUUID();

            // DAO 값 초기화
            timer.setGameId(newGameId);
            timer.setUserId(userId);
            timer.setStopTime("0000");
            timer.setErrorRange("9999");
            //timer.setCreateTime(LocalDateTime.now());
            //timer.setUploadTime(LocalDateTime.now());
            timer.setCreateTime(LocalDateTime.now().plusHours(9));
            timer.setUploadTime(LocalDateTime.now().plusHours(9));

            String targetTime = saveTargetTimerBean.exec();
            timer.setTargetTime(targetTime);

            // DTO 객체를 DAO를 이용해 초기화
            responseTimerDTO.setUserId(userId);
            responseTimerDTO.setGameId(newGameId);
            responseTimerDTO.setTargetTime(targetTime);

            // DAO 저장
            jpaTimerRepository.save(timer);
        }

        // DTO 반환
        return responseTimerDTO;
    }


}
