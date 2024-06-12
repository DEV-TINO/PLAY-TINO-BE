package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.Timer;
import com.DevTino.play_tino.timer.domain.entity.TimerRank;
import com.DevTino.play_tino.timer.repository.JpaTimerRankRepository;
import com.DevTino.play_tino.timer.repository.JpaTimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class GetTimerDAOsBean {

    JpaTimerRepository jpaTimerRepository;
    JpaTimerRankRepository jpaTimerRankRepository;

    @Autowired
    public GetTimerDAOsBean(JpaTimerRepository jpaTimerRepository, JpaTimerRankRepository jpaTimerRankRepository){
        this.jpaTimerRepository = jpaTimerRepository;
        this.jpaTimerRankRepository = jpaTimerRankRepository;
    }

    public List<Timer> exec(Pageable pageable){

        // timerRank 객체 생성
        // 여기서 100개 가져와
        List<TimerRank> timerRankList = jpaTimerRankRepository.findAll();

        // timerId로 이루어진 리스트 객체 생성
        List<UUID> timerIds = new ArrayList<>();
        for (TimerRank timerRank : timerRankList){
            timerIds.add(timerRank.getTimerId());
        }

        // page형의 timerPage 가져오기
        List<Timer> timerList = jpaTimerRepository.findByGameIdInOrderByErrorRangeAscUploadTimeDesc(timerIds);
        if (timerList.size() < 100){
            return timerList;
        }

        return timerList.subList(0, 100);

        // 반환
    }
}