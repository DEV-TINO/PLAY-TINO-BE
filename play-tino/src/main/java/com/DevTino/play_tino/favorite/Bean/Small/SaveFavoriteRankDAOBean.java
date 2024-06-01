package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteRank;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.others.FavoriteCheckValidFavoriteRankBean;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveFavoriteRankDAOBean {

    JpaFavoriteRankRepository favoriteRankRepository;
    FavoriteCheckValidFavoriteRankBean favoriteCheckValidFavoriteRankBean;

    @Autowired
    public SaveFavoriteRankDAOBean(JpaFavoriteRankRepository favoriteRankRepository, FavoriteCheckValidFavoriteRankBean favoriteCheckValidFavoriteRankBean){
        this.favoriteRankRepository = favoriteRankRepository;
        this.favoriteCheckValidFavoriteRankBean = favoriteCheckValidFavoriteRankBean;
    }

    // FavoriteRank 저장, Success(성공 여부) 반환
    public FavoriteResponseSuccess exec(FavoriteRank favoriteRankDAO){
        //responseSuccess 생성
        FavoriteResponseSuccess favoriteResponseSuccess = new FavoriteResponseSuccess();

        //favoriteRank의 값들이 유효한지 검사해서, 결과에 따라 '성공 여부'를 설정
        favoriteResponseSuccess.setSuccess(favoriteCheckValidFavoriteRankBean.exec(favoriteRankDAO));

        //성공한 경우만 DAO 저장
        if(favoriteResponseSuccess.getSuccess()) favoriteRankRepository.save(favoriteRankDAO);

        //'성공 여부'가 설정된 responseSuccess 반환
        return favoriteResponseSuccess;
    }
}
