package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFavoriteCommentDAOBean {

    JpaFavoriteCommentRepository jpaFavoriteCommentRepository;

    @Autowired
    public GetFavoriteCommentDAOBean(JpaFavoriteCommentRepository jpaFavoriteCommentRepository){
        this.jpaFavoriteCommentRepository = jpaFavoriteCommentRepository;
    }

    // commentId에 해당하는 댓글을 찾아서 반환하는 메서드
    public FavoriteComment exec(UUID commentId){

        return jpaFavoriteCommentRepository.findById(commentId).get();
    }
}
