package com.DevTino.play_tino.favorite.others;

import com.DevTino.play_tino.favorite.Bean.Small.GetFavoriteDAOBean;
import com.DevTino.play_tino.favorite.Bean.Small.GetFavoriteGameDAOBean;
import com.DevTino.play_tino.favorite.domain.FavoriteRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FavoriteCheckValidFavoriteRankBean {

    GetFavoriteGameDAOBean getFavoriteGameDAOBean;
    GetFavoriteDAOBean getFavoriteDAOBean;

    @Autowired
    public FavoriteCheckValidFavoriteRankBean(GetFavoriteGameDAOBean getFavoriteGameDAOBean, GetFavoriteDAOBean getFavoriteDAOBean){
        this.getFavoriteGameDAOBean = getFavoriteGameDAOBean;
        this.getFavoriteDAOBean = getFavoriteDAOBean;
    }

    // FavoriteRank DAO 값이 유효한지 확인하는 기능
    public Boolean exec(FavoriteRank favoriteRankDAO){

        // gameId가 존재하는 게임인지 확인하기 위해 gameId로 FavoriteGame 검색하고
        // favoriteId가 존재하는 컨텐트인지 확인하기 위해 favoriteId로 Favorite 검색
        // -> 둘 다 null이 아니면 true 반환
        if(
                getFavoriteGameDAOBean.exec(favoriteRankDAO.getGameId()) != null
                && getFavoriteDAOBean.exec(favoriteRankDAO.getFavoriteId()) != null
        ) {
            return true;
        }

        // null이 하나라도 있다면 false 반환
        else return false;

    }
}
