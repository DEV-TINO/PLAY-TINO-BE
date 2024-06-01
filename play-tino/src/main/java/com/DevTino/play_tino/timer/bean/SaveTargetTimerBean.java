package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.repository.JpaTimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SaveTargetTimerBean {

    JpaTimerRepository jpaTimerRepository;

    @Autowired
    public SaveTargetTimerBean(JpaTimerRepository jpaTimerRepository){
        this.jpaTimerRepository = jpaTimerRepository;
    }

    public String exec(){

        Random random = new Random();

        String targetTime = String.valueOf(random.nextInt(11)+5);

        // 한자리수일떄 앞에 0을 붙여야겠지?
        if (targetTime.length() < 2)
            targetTime = "0" + targetTime + ".00";
        else targetTime = targetTime + ".00";

        return targetTime;
    }
}
