package com.DevTino.play_tino.favorite.controller;

import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteCommentHeartDeleteDTO;
import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteCommentHeartSaveDTO;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.service.FavoriteCommentHeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://play-tino.com")
@RequestMapping("/favorite")
public class FavoriteCommentHeartController {

    private final FavoriteCommentHeartService favoriteCommentHeartService;

    @Autowired
    public FavoriteCommentHeartController(FavoriteCommentHeartService favoriteCommentHeartService){
        this.favoriteCommentHeartService = favoriteCommentHeartService;
    }

    //댓글좋아요 저장
    @PostMapping("/comment-heart")
    public FavoriteResponseSuccess createHeart(@RequestBody RequestFavoriteCommentHeartSaveDTO requestFavoriteCommentHeartSaveDTO){

        return favoriteCommentHeartService.createHeart(requestFavoriteCommentHeartSaveDTO);
    }

    //댓글좋아요 삭제
    @DeleteMapping("/comment-heart")
    public FavoriteResponseSuccess deleteHeart(@RequestBody RequestFavoriteCommentHeartDeleteDTO requestFavoriteCommentHeartDeleteDTO){

        return favoriteCommentHeartService.deleteHeart(requestFavoriteCommentHeartDeleteDTO);
    }
}
