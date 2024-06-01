package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.Favorite;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFavoriteDAOBean {

    JpaFavoriteRepository jpaFavoriteRepository;

    @Autowired
    public GetFavoriteDAOBean(JpaFavoriteRepository jpaFavoriteRepository){
        this.jpaFavoriteRepository = jpaFavoriteRepository;
    }

    public Favorite exec(UUID favoriteId){
        return jpaFavoriteRepository.findById(favoriteId).get();
    }
}
