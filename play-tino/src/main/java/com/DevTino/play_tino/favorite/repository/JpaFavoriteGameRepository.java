package com.DevTino.play_tino.favorite.repository;

import com.DevTino.play_tino.favorite.domain.FavoriteGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaFavoriteGameRepository extends JpaRepository<FavoriteGame, UUID> {

}
