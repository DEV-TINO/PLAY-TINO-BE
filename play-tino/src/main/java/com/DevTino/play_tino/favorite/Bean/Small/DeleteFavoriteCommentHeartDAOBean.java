package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteCommentHeart;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteFavoriteCommentHeartDAOBean {

    JpaFavoriteCommentHeartRepository jpaFavoriteCommentHeartRepository;

    @Autowired
    public DeleteFavoriteCommentHeartDAOBean(JpaFavoriteCommentHeartRepository jpaFavoriteCommentHeartRepository){
        this.jpaFavoriteCommentHeartRepository = jpaFavoriteCommentHeartRepository;
    }

    // 댓글좋아요 삭제
    public FavoriteResponseSuccess exec(FavoriteCommentHeart favoriteCommentHeart){

        //responseSuccess 생성
        FavoriteResponseSuccess favoriteResponseSuccess = new FavoriteResponseSuccess();

        //'성공 여부'를 true로 설정
        favoriteResponseSuccess.setSuccess(true);

        //성공한 경우만 댓글좋아요(DAO) 삭제
        jpaFavoriteCommentHeartRepository.delete(favoriteCommentHeart);

        //'성공 여부'가 설정된 responseSuccess 반환
        return favoriteResponseSuccess;
    }
}
