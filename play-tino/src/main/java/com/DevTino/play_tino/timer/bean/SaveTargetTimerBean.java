package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.repository.JpaTimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SaveTargetTimerBean {

    public static final int MAX_STRING_LENGTH = 2;

    JpaTimerRepository jpaTimerRepository;

    @Autowired
    public SaveTargetTimerBean(JpaTimerRepository jpaTimerRepository){
        this.jpaTimerRepository = jpaTimerRepository;
    }

    public String exec(){

        Random random = new Random();

        String targetTime = String.valueOf(random.nextInt(51)+50); // 50 ~ 100

        if (targetTime.length() > MAX_STRING_LENGTH) {
            return Integer.valueOf(targetTime)/10 + ".00";
        } else {
            return "0" + Integer.toString(Integer.valueOf(targetTime)/10) + "." + Integer.toString(Integer.valueOf(targetTime) % 10) + "0";
        }
    }
}
