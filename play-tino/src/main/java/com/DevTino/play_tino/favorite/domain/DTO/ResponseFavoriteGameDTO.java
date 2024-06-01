package com.DevTino.play_tino.favorite.domain.DTO;

import com.DevTino.play_tino.favorite.domain.Favorite;
import com.DevTino.play_tino.favorite.others.FavoriteStringListConverter;
import jakarta.persistence.Convert;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ResponseFavoriteGameDTO {
    UUID gameId;

    @Convert(converter = FavoriteStringListConverter.class)
    List<Favorite> favoriteList;
}
