package com.DevTino.play_tino.favorite.domain.DTO;

import com.DevTino.play_tino.favorite.others.FavoriteStringListConverter;
import jakarta.persistence.Convert;
import lombok.Data;

import java.util.List;

@Data
public class ResponseFavoriteRanksDTO {
    Integer favoriteRankTotal;  // 전체 favorite 개수

    @Convert(converter = FavoriteStringListConverter.class)
    List<ResponseFavoriteRankDTO> rankList;    // 선택된 비율을 포함하는 Favorite 리스트

}
