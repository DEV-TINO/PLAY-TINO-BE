package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateFavoriteCommentHeartCountDAOBean {

    GetFavoriteCommentDAOBean getFavoriteCommentDAOBean;

    @Autowired
    public UpdateFavoriteCommentHeartCountDAOBean(GetFavoriteCommentDAOBean getFavoriteCommentDAOBean){
        this.getFavoriteCommentDAOBean = getFavoriteCommentDAOBean;
    }

    //댓글좋아요 추가(+1)
    public void heartCountUp(FavoriteComment favoriteComment){
        //DAO의 댓글좋아요개수(HeartCount) +1
        favoriteComment.setHeartCount(favoriteComment.getHeartCount()+1);
    }

    //댓글좋아요 삭제(-1)
    public void heartCountDown(FavoriteComment favoriteComment){
        //DAO의 댓글좋아요개수(HeartCount) -1
        favoriteComment.setHeartCount(favoriteComment.getHeartCount()-1);
    }
}
