package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.Favorite;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRandomFavoriteDAOsBean {

    JpaFavoriteRepository jpaFavoriteRepository;

    @Autowired
    public GetRandomFavoriteDAOsBean(JpaFavoriteRepository jpaFavoriteRepository){
        this.jpaFavoriteRepository = jpaFavoriteRepository;
    }

    // Favorite DAO 랜덤 검색
    public List<Favorite> exec(){
        return jpaFavoriteRepository.findFavorites();
    }

}
