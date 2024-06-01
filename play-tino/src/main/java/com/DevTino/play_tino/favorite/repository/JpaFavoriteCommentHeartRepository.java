package com.DevTino.play_tino.favorite.repository;

import com.DevTino.play_tino.favorite.domain.FavoriteCommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaFavoriteCommentHeartRepository extends JpaRepository<FavoriteCommentHeart, UUID> {

    //commentId, userId로 CommentHeart 찾기
    public FavoriteCommentHeart findByCommentIdAndUserId(UUID commentId, UUID userId);
}
