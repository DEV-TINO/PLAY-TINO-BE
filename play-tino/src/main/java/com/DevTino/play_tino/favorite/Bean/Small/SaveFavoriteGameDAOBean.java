package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteGame;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveFavoriteGameDAOBean {

    JpaFavoriteGameRepository jpaFavoriteGameRepository;

    @Autowired
    public SaveFavoriteGameDAOBean(JpaFavoriteGameRepository jpaFavoriteGameRepository){
        this.jpaFavoriteGameRepository = jpaFavoriteGameRepository;
    }

    // 게임 DAO 저장
    public void exec(FavoriteGame favoriteGame){
        jpaFavoriteGameRepository.save(favoriteGame);
    }
}
