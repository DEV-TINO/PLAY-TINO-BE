package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteCount;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetFavoriteCountDAOsBean {

    JpaFavoriteCountRepository jpaFavoriteCountRepository;

    @Autowired
    public GetFavoriteCountDAOsBean(JpaFavoriteCountRepository jpaFavoriteCountRepository){
        this.jpaFavoriteCountRepository = jpaFavoriteCountRepository;
    }

    // 검색 - 페이징
    public Page<FavoriteCount> exec(Pageable pageable){
        return jpaFavoriteCountRepository.findAll(pageable);
    }

}
