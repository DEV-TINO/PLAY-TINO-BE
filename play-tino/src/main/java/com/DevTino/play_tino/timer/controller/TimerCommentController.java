package com.DevTino.play_tino.timer.controller;

import com.DevTino.play_tino.timer.domain.DTO.*;
import com.DevTino.play_tino.timer.domain.entity.TimerResponseSuccess;
import com.DevTino.play_tino.timer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://play-tino.com") // 배포
//@CrossOrigin("*") // 로컬
@RequestMapping("/timer")
public class TimerCommentController {

    private final CommentService commentService;

    @Autowired
    public TimerCommentController(CommentService commentService){
        this.commentService = commentService;
    }

    // 댓글 1개 조회
    @GetMapping("/comment/{commentId}")
    public ResponseTimerCommentDTO read(@PathVariable UUID commentId){
        return commentService.read(commentId);
    }

    // 댓글 전체 조회 - 좋아요순 - heart : 좋아요순, upload : 최신순
    @GetMapping("/comment/all/user/{userId}/{type}")
    public ResponseTimerCommentsDTO readAll(@PathVariable String type, @PathVariable UUID userId, @RequestParam (required = false) Integer page)
    {
        if (type.equals("heart"))
            type = "heartCount";
        else if (type.equals("time")) {
            type = "uploadTime";
        }
        else {
            return null;
        }
        Pageable pageable = PageRequest.of(page, 10,
                Sort.by(Sort.Direction.DESC, type)
        );

        return commentService.readAll(type, userId, pageable);
    }

    // 댓글 저장
    @PostMapping("/comment")
    public TimerResponseSuccess save(@RequestBody RequestTimerCommentSaveDTO requestTimerCommentSaveDTO){
        return commentService.save(requestTimerCommentSaveDTO);
    }

    // 댓글 수정
    @PutMapping("/comment")
    public TimerResponseSuccess update(@RequestBody RequestTimerCommentUpdateDTO requestTimerCommentUpdateDTO){
        return commentService.update(requestTimerCommentUpdateDTO);
    }

    // 댓글 삭제
    @DeleteMapping("/comment")
    public TimerResponseSuccess delete(@RequestBody RequestTimerCommentDeleteDTO requestTimerCommentDeleteDTO){
        return commentService.delete(requestTimerCommentDeleteDTO);
    }

}
