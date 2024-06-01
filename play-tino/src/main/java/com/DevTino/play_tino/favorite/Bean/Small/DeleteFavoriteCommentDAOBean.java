package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.others.FavoriteCheckUserCommentBean;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteFavoriteCommentDAOBean {

    JpaFavoriteCommentRepository jpaFavoriteCommentRepository;
    FavoriteCheckUserCommentBean favoriteCheckUserCommentBean;

    @Autowired
    public DeleteFavoriteCommentDAOBean(JpaFavoriteCommentRepository jpaFavoriteCommentRepository, FavoriteCheckUserCommentBean favoriteCheckUserCommentBean){
        this.jpaFavoriteCommentRepository = jpaFavoriteCommentRepository;
        this.favoriteCheckUserCommentBean = favoriteCheckUserCommentBean;
    }

    //댓글 삭제
    public FavoriteResponseSuccess exec(FavoriteComment favoriteComment, UUID userId){
        //responseSuccess 생성
        FavoriteResponseSuccess favoriteResponseSuccess = new FavoriteResponseSuccess();

        //댓글이 유저가 작성한 댓글이 맞는지 확인 -> 결과에 따라 '성공 여부' 설정
        if(favoriteCheckUserCommentBean.exec(favoriteComment, userId)) favoriteResponseSuccess.setSuccess(true);
        else favoriteResponseSuccess.setSuccess(false);

        //성공한 경우만 DAO 삭제
        if((favoriteResponseSuccess.getSuccess())) jpaFavoriteCommentRepository.delete(favoriteComment);

        //'성공 여부'가 설정된 responseSuccess 반환
        return favoriteResponseSuccess;
    }
}
