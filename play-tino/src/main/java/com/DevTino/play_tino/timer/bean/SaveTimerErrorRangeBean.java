package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.DTO.RequestTimerSaveDTO;
import com.DevTino.play_tino.timer.repository.JpaTimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveTimerErrorRangeBean {

    JpaTimerRepository jpaTimerRepository;

    @Autowired
    public SaveTimerErrorRangeBean(JpaTimerRepository jpaTimerRepository) {
        this.jpaTimerRepository = jpaTimerRepository;
    }

    public String exec(RequestTimerSaveDTO requestTimerSaveDTO) {

        String targetTime = requestTimerSaveDTO.getTargetTime();
        String stopTime = requestTimerSaveDTO.getStopTime();
        String errorRange;

        if(Double.parseDouble(targetTime) > Double.parseDouble(stopTime)) {
            errorRange = String.format("%05.2f", Double.parseDouble(targetTime) - Double.parseDouble(stopTime));
        } else {
            errorRange = String.format("%05.2f", Double.parseDouble(stopTime) - Double.parseDouble(targetTime));
        }
        return errorRange;
    }
}