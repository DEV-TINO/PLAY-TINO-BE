package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.DTO.ResponseSaveTimerRankDTO;
import com.DevTino.play_tino.timer.domain.entity.TimerRank;
import com.DevTino.play_tino.timer.repository.JpaTimerRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveTimerRankDAOBean {

    JpaTimerRankRepository jpaTimerRankRepository;
    CheckVaildTimerRankBean checkVaildTimerRankBean;

    @Autowired
    public SaveTimerRankDAOBean(JpaTimerRankRepository jpaTimerRankRepository, CheckVaildTimerRankBean checkVaildTimerRankBean){
        this.jpaTimerRankRepository = jpaTimerRankRepository;
        this.checkVaildTimerRankBean = checkVaildTimerRankBean;
    }

    public ResponseSaveTimerRankDTO exec(TimerRank timerRank){

        // responseSuccess 객체 생성
        ResponseSaveTimerRankDTO responseSaveTimerRankDTO = new ResponseSaveTimerRankDTO();

        responseSaveTimerRankDTO.setSuccess(checkVaildTimerRankBean.exec(timerRank));
        responseSaveTimerRankDTO.setRankIn(true);

        // timerRank 레파지토리 저장
        jpaTimerRankRepository.save(timerRank);

        return responseSaveTimerRankDTO;
    }

}
