package com.DevTino.play_tino.favorite.controller;

import com.DevTino.play_tino.favorite.domain.DTO.ResponseFavoriteGameDTO;
import com.DevTino.play_tino.favorite.service.FavoriteGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
//@CrossOrigin(origins = "https://play-tino.com") // 배포
@CrossOrigin("*") // 로컬
@RequestMapping("/favorite")
public class FavoriteGameController {

    FavoriteGameService favoriteGameService;

    @Autowired
    public FavoriteGameController(FavoriteGameService favoriteGameService){
        this.favoriteGameService = favoriteGameService;
    }

    // 게임 시작 [컨텐츠 16개 조회]
    @GetMapping("/start/user/{userId}")
    public ResponseFavoriteGameDTO createGame(@PathVariable UUID userId, @RequestParam(required = false) UUID gameId){

        return favoriteGameService.createGame(userId, gameId);
    }
}
