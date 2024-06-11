package com.DevTino.play_tino.timer.bean;

import com.DevTino.play_tino.timer.domain.entity.TimerComment;
import com.DevTino.play_tino.timer.repository.JpaTimerCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetTimerCommentDAOsBean {

    JpaTimerCommentRepository jpaTimerCommentRepository;

    @Autowired
    public GetTimerCommentDAOsBean(JpaTimerCommentRepository jpaTimerCommentRepository){
        this.jpaTimerCommentRepository = jpaTimerCommentRepository;
    }

    public Page<TimerComment> exec(Pageable pageable, String type){
        if (type.equals("heartCount"))
            return jpaTimerCommentRepository.findAllByOrderByHeartCountDescUploadTimeDesc(pageable);
        else if (type.equals("uploadTime")) {
            return jpaTimerCommentRepository.findAllByOrderByUploadTimeDesc(pageable);        }
        else {
            return null;
        }
    }
}
