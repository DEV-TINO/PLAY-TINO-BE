package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteCommentHeart;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.others.FavoriteCheckValidCommentHeartBean;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveFavoriteCommentHeartDAOBean {

    JpaFavoriteCommentHeartRepository jpaFavoriteCommentHeartRepository;
    FavoriteCheckValidCommentHeartBean favoriteCheckValidCommentHeartBean;

    @Autowired
    public SaveFavoriteCommentHeartDAOBean(JpaFavoriteCommentHeartRepository jpaFavoriteCommentHeartRepository, FavoriteCheckValidCommentHeartBean favoriteCheckValidCommentHeartBean){
        this.jpaFavoriteCommentHeartRepository = jpaFavoriteCommentHeartRepository;
        this.favoriteCheckValidCommentHeartBean = favoriteCheckValidCommentHeartBean;
    }

    //댓글좋아요 저장
    public FavoriteResponseSuccess exec(FavoriteCommentHeart favoriteCommentHeart){
        //responseSuccess 생성
        FavoriteResponseSuccess favoriteResponseSuccess = new FavoriteResponseSuccess();

        //commentHeart의 값들이 유효한지 검사해서, 결과에 따라 '성공 여부'를 설정
        favoriteResponseSuccess.setSuccess(favoriteCheckValidCommentHeartBean.exec(favoriteCommentHeart));

        //성공한 경우만 DAO 저장
        if(favoriteResponseSuccess.getSuccess()) jpaFavoriteCommentHeartRepository.save(favoriteCommentHeart);

        //'성공 여부'가 설정된 responseSuccess 반환
        return favoriteResponseSuccess;
    }
}
