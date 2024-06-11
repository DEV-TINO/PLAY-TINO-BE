package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFavoriteCommentDAOsBean {
    
    JpaFavoriteCommentRepository jpaFavoriteCommentRepository;
    
    @Autowired
    public GetFavoriteCommentDAOsBean(JpaFavoriteCommentRepository jpaFavoriteCommentRepository){
        this.jpaFavoriteCommentRepository = jpaFavoriteCommentRepository;
    }
    
    // 모든 댓글을 찾아서 List로 반환하는 메서드
    public List<FavoriteComment> exec(){
        
        return jpaFavoriteCommentRepository.findAll();
    }

    // 모든 댓글을 uploadTime을 기준으로 정렬 검색해 List로 반환하는 메서드
    public List<FavoriteComment> execByTime(){

        return jpaFavoriteCommentRepository.findAllByOrderByUploadTimeDesc();
    }

    // 모든 댓글을 heartCount를 기준으로 정렬 검색해 List로 반환하는 메서드
    public List<FavoriteComment> execByHeart(){

        return jpaFavoriteCommentRepository.findAllByOrderByHeartCountDescUploadTimeDesc();
    }

    // 댓글을 페이지로 반환하는 메서드
    public Page<FavoriteComment> exec(Pageable pageable){

        return jpaFavoriteCommentRepository.findAll(pageable);
    }

    //하트 개수로 내림차순하고 페이징 처리
    public Page<FavoriteComment> execByHeartCount(Pageable pageable) {
        return jpaFavoriteCommentRepository.findAllByOrderByHeartCountDescUploadTimeDesc(pageable);
    }

    //업로드 시간으로 내림차순하고 페이징 처리
    public Page<FavoriteComment> execByUploadTime(Pageable pageable) {
        return jpaFavoriteCommentRepository.findAllByOrderByUploadTimeDesc(pageable);
    }
}
