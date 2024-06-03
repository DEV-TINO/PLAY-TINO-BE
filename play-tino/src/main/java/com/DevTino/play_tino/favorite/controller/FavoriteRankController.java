package com.DevTino.play_tino.favorite.controller;

import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteRankDTO;
import com.DevTino.play_tino.favorite.domain.DTO.ResponseFavoriteRanksDTO;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.service.FavoriteRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "https://play-tino.com") // 배포
@CrossOrigin("*") // 로컬
@RequestMapping("/favorite")
public class FavoriteRankController {

    FavoriteRankService favoriteRankService;

    @Autowired
    public FavoriteRankController(FavoriteRankService favoriteRankService){
        this.favoriteRankService = favoriteRankService;
    }

    // 랭킹 저장
    @PostMapping("/rank")
    public FavoriteResponseSuccess saveRank(@RequestBody RequestFavoriteRankDTO requestFavoriteRankDTO){

        return favoriteRankService.saveRank(requestFavoriteRankDTO);
    }

    // 랭킹 조회 - 페이징
    @GetMapping("/rank/all")
    public ResponseFavoriteRanksDTO readRankAll(@RequestParam(required = false, defaultValue = "0", value = "page") Integer pageNo){
        return favoriteRankService.readRankAll(pageNo);
    }

}
