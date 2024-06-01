package com.DevTino.play_tino.favorite.others;

import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FavoriteCheckUserCommentBean {

    //댓글이 사용자가 작성한 댓글인지 확인하는 기능
    public Boolean exec(FavoriteComment favoriteComment, UUID userId){

        //userId를 댓글의 userId와 비교 -> 같으면 true, 아니면 false 반환
        return favoriteComment.getUserId().equals(userId);
    }
}
