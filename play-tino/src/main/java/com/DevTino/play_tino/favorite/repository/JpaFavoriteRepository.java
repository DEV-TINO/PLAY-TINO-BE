package com.DevTino.play_tino.favorite.repository;

import com.DevTino.play_tino.favorite.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface JpaFavoriteRepository extends JpaRepository<Favorite, UUID> {

    // 레코드 랜덤 검색
    @Query(value = "SELECT favorite FROM Favorite favorite order by RAND() limit 16")
    public List<Favorite> findFavorites();
}
