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

    public Page<TimerComment> exec(Pageable pageable){
        return jpaTimerCommentRepository.findAll(pageable);
    }

    //모든 댓글 업로드 시간 기준으로 정렬해 리스트 반환
    public List<TimerComment> exec(String type){
        if (type.equals("heartCount"))
            return jpaTimerCommentRepository.findAllByOrderByHeartCountDescUploadTimeDesc();
        else if (type.equals("uploadTime")) {
            return jpaTimerCommentRepository.findAllByOrderByUploadTimeDesc();
        }
        else {
            return null;
        }
    }
}
