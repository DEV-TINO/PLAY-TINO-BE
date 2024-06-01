package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteCommentHeart;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFavoriteCommentHeartDAOBean {

    JpaFavoriteCommentHeartRepository jpaFavoriteCommentHeartRepository;

    @Autowired
    public GetFavoriteCommentHeartDAOBean(JpaFavoriteCommentHeartRepository jpaFavoriteCommentHeartRepository){
        this.jpaFavoriteCommentHeartRepository = jpaFavoriteCommentHeartRepository;
    }

    public FavoriteCommentHeart exec(UUID commentId, UUID userId){
        return jpaFavoriteCommentHeartRepository.findByCommentIdAndUserId(commentId, userId);
    }
}
