package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.Favorite;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFavoriteDAOsBean {

    JpaFavoriteRepository jpaFavoriteRepository;

    @Autowired
    public GetFavoriteDAOsBean(JpaFavoriteRepository jpaFavoriteRepository){
        this.jpaFavoriteRepository = jpaFavoriteRepository;
    }

    public List<Favorite> exec(){
        return jpaFavoriteRepository.findAll();
    }
}
