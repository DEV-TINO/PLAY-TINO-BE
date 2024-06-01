package com.DevTino.play_tino.favorite.others;

import com.DevTino.play_tino.favorite.domain.FavoriteCommentHeart;
import org.springframework.stereotype.Component;

@Component
public class FavoriteCheckValidCommentHeartBean {

    //CommentHeart DAO 값이 유효한지 [null이 없는지] 확인하는 기능
    public Boolean exec(FavoriteCommentHeart favoriteCommentHeart){

        //DAO 값 모두 null이 아니라면 true 반환
        if(
                favoriteCommentHeart.getCommentHeartId() != null
                && favoriteCommentHeart.getCommentId() != null
                && favoriteCommentHeart.getUserId() != null
        ){
            return true;
        }

        //null이 하나라도 있다면 false 반환
        else return false;
    }
}