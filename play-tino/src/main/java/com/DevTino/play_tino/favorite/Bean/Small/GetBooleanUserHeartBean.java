package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteCommentHeart;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetBooleanUserHeartBean {

    JpaFavoriteCommentHeartRepository jpaFavoriteCommentHeartRepository;

    @Autowired
    public GetBooleanUserHeartBean(JpaFavoriteCommentHeartRepository jpaFavoriteCommentHeartRepository){
        this.jpaFavoriteCommentHeartRepository = jpaFavoriteCommentHeartRepository;
    }

    //댓글에 대한 유저의 하트가 존재하는지 체크
    public Boolean checkUserHeart(UUID commentId, UUID userId){

        //유저가 댓글에 누른 하트를 찾음
        FavoriteCommentHeart favoriteCommentHeart = jpaFavoriteCommentHeartRepository.findByCommentIdAndUserId(commentId, userId);

        //해당하는 하트가 있으면 true, 없으면 false 반환
        //return commentHeart.getCommentHeartId() != null;
        return favoriteCommentHeart != null;
    }
}
