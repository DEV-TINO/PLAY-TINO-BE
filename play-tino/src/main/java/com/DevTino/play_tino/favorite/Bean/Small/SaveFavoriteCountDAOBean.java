package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteCount;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveFavoriteCountDAOBean {

    JpaFavoriteCountRepository jpaFavoriteCountRepository;

    @Autowired
    public SaveFavoriteCountDAOBean(JpaFavoriteCountRepository jpaFavoriteCountRepository){
        this.jpaFavoriteCountRepository = jpaFavoriteCountRepository;
    }

    // DAO 저장
    public void exec(FavoriteCount favoriteCount){
        jpaFavoriteCountRepository.save(favoriteCount);
    }
}
