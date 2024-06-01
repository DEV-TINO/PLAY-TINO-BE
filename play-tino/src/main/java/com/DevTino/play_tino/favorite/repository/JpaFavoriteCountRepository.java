package com.DevTino.play_tino.favorite.repository;

import com.DevTino.play_tino.favorite.domain.FavoriteCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaFavoriteCountRepository extends JpaRepository<FavoriteCount, UUID> {

    // 페이징해서 FavoriteCount 찾기
    Page<FavoriteCount> findAll(Pageable pageable);
}
