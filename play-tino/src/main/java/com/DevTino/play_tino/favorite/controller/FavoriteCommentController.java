package com.DevTino.play_tino.favorite.controller;

import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteCommentDeleteDTO;
import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteCommentSaveDTO;
import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteCommentUpdateDTO;
import com.DevTino.play_tino.favorite.domain.DTO.ResponseFavoriteCommentsDTO;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.service.FavoriteCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://play-tino.com") // 배포
//@CrossOrigin("*") // 로컬
@RequestMapping("/favorite")
public class FavoriteCommentController {
    private final FavoriteCommentService favoriteCommentService;

    @Autowired
    public FavoriteCommentController(FavoriteCommentService favoriteCommentService) {
        this.favoriteCommentService = favoriteCommentService;
    }


    // 댓글 조회 - 페이징
    @GetMapping("/comment/all/user/{userId}/{type}")
    public ResponseFavoriteCommentsDTO readCommentAll(@PathVariable UUID userId,
                                                      @PathVariable String type,
                                                      @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo){
        return favoriteCommentService.readCommentAll(userId, pageNo, type);
    }

    //댓글 저장
    @PostMapping("/comment")
    public FavoriteResponseSuccess createComment(@RequestBody RequestFavoriteCommentSaveDTO requestFavoriteCommentSaveDTO){

        return favoriteCommentService.createComment(requestFavoriteCommentSaveDTO);
    }

    //댓글 수정
    @PutMapping("/comment")
    public FavoriteResponseSuccess updateComment(@RequestBody RequestFavoriteCommentUpdateDTO requestFavoriteCommentUpdateDTO){

        return favoriteCommentService.updateComment(requestFavoriteCommentUpdateDTO);
    }

    //댓글 삭제
    @DeleteMapping("/comment")
    public FavoriteResponseSuccess deleteComment(@RequestBody RequestFavoriteCommentDeleteDTO requestFavoriteCommentDeleteDTO){

        return favoriteCommentService.deleteComment(requestFavoriteCommentDeleteDTO);
    }

}
