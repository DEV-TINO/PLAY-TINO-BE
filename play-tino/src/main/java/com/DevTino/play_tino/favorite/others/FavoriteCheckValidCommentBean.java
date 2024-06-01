package com.DevTino.play_tino.favorite.others;

import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import org.springframework.stereotype.Component;

@Component
public class FavoriteCheckValidCommentBean {

    //Comment DAO 값이 유효한지 [null이 없는지] 확인하는 기능
    public Boolean exec(FavoriteComment favoriteComment){

        //DAO 값 모두 null이 아니라면 true 반환
        if(
                favoriteComment.getCommentId() != null
                && favoriteComment.getUserId() != null
                && favoriteComment.getContent() != null
                && favoriteComment.getHeartCount() != null
                && favoriteComment.getUploadTime() != null
        ){
            return true;
        }

        //null이 하나라도 있다면 false 반환
        else return false;
    }
}
