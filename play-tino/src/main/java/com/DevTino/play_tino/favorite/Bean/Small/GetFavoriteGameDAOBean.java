package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteGame;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFavoriteGameDAOBean {

    JpaFavoriteGameRepository jpaFavoriteGameRepository;

    @Autowired
    public GetFavoriteGameDAOBean(JpaFavoriteGameRepository jpaFavoriteGameRepository){
        this.jpaFavoriteGameRepository = jpaFavoriteGameRepository;
    }

    public FavoriteGame exec(UUID gameId){
        return jpaFavoriteGameRepository.findById(gameId).get();
    }
}
