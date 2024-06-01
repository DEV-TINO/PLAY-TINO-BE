package com.DevTino.play_tino.timer.controller;

import com.DevTino.play_tino.timer.domain.DTO.RequestTimerCommentHeartDeleteDTO;
import com.DevTino.play_tino.timer.domain.DTO.RequestTimerCommentHeartSaveDTO;
import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import com.DevTino.play_tino.timer.service.CommentHeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://play-tino.com")
@RequestMapping("/timer")
public class TimerCommentHeartController {

    private final CommentHeartService commentHeartService;

    @Autowired
    public TimerCommentHeartController(CommentHeartService commentHeartService){
        this.commentHeartService = commentHeartService;
    }

    // 댓글 좋아요 생성 o
    @PostMapping("/comment-heart")
    public TimerResponseSuccess save(@RequestBody RequestTimerCommentHeartSaveDTO requestTimerCommentHeartSaveDTO){
        return commentHeartService.save(requestTimerCommentHeartSaveDTO);
    }

    // 댓글 좋아요 제거 o
    @DeleteMapping("/comment-heart")
    public TimerResponseSuccess delete(@RequestBody RequestTimerCommentHeartDeleteDTO requestTimerCommentHeartDeleteDTO){
        return commentHeartService.delete(requestTimerCommentHeartDeleteDTO);
    }
}