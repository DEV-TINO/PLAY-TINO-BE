package com.DevTino.play_tino.quiz.Bean;

import com.DevTino.play_tino.quiz.domain.QuizComment;
import com.DevTino.play_tino.quiz.repository.JpaQuizCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class GetQuizCommentsDAOBean {
    JpaQuizCommentRepository jpaQuizCommentRepository;

    @Autowired
    public GetQuizCommentsDAOBean(JpaQuizCommentRepository jpaQuizCommentRepository) {
        this.jpaQuizCommentRepository = jpaQuizCommentRepository;
    }

    //업로드 시간으로 내림차순하고 페이징 처리
    public Page<QuizComment> execUpLoadTime(Pageable pageable) {
        return jpaQuizCommentRepository.findAllByOrderByUploadTimeDesc(pageable);
    }

    //하트 개수로 내림차순하고 페이징 처리
    public Page<QuizComment> execHeartCount(Pageable pageable) {
        return jpaQuizCommentRepository.findAllByOrderByHeartCountDescUploadTimeDesc(pageable);
    }
}
