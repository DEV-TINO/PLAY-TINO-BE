package com.DevTino.play_tino.favorite.repository;

import com.DevTino.play_tino.favorite.domain.FavoriteRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaFavoriteRankRepository extends JpaRepository<FavoriteRank, UUID> {

    // favoriteId로 favoriteRankList 찾기
    List<FavoriteRank> findByFavoriteId(UUID favoriteId);
}
