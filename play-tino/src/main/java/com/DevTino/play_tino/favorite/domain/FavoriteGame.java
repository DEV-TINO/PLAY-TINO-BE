package com.DevTino.play_tino.favorite.domain;

import com.DevTino.play_tino.favorite.others.FavoriteStringListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoriteGame {
    @Id
    UUID gameId;

    UUID userId;

    @Convert(converter = FavoriteStringListConverter.class)
    List<Favorite> favoriteList;
}
