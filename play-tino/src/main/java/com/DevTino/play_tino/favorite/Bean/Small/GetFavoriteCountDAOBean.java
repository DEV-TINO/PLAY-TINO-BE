package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteCount;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetFavoriteCountDAOBean {

    JpaFavoriteCountRepository jpaFavoriteCountRepository;

    @Autowired
    public GetFavoriteCountDAOBean(JpaFavoriteCountRepository jpaFavoriteCountRepository) {
        this.jpaFavoriteCountRepository = jpaFavoriteCountRepository;
    }

    // favoriteId에 해당하는 FavoriteCount를 찾아서 반환하는 메서드
    public FavoriteCount exec(UUID favoriteId) {
        return jpaFavoriteCountRepository.findById(favoriteId).get();
    }
}
