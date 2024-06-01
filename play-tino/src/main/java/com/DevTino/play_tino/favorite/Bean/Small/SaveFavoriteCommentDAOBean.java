package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.others.FavoriteCheckValidCommentBean;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveFavoriteCommentDAOBean {

    JpaFavoriteCommentRepository jpaFavoriteCommentRepository;
    FavoriteCheckValidCommentBean favoriteCheckValidCommentBean;

    @Autowired
    public SaveFavoriteCommentDAOBean(JpaFavoriteCommentRepository jpaFavoriteCommentRepository, FavoriteCheckValidCommentBean favoriteCheckValidCommentBean){
        this.jpaFavoriteCommentRepository = jpaFavoriteCommentRepository;
        this.favoriteCheckValidCommentBean = favoriteCheckValidCommentBean;
    }

    // 댓글 저장
    public FavoriteResponseSuccess exec(FavoriteComment favoriteComment){

        //responseSuccess 생성
        FavoriteResponseSuccess favoriteResponseSuccess = new FavoriteResponseSuccess();

        //comment의 값들이 유효한지 검사해서, 결과에 따라 '성공 여부'를 설정
        favoriteResponseSuccess.setSuccess(favoriteCheckValidCommentBean.exec(favoriteComment));

        //성공한 경우만 DAO 저장
        if(favoriteResponseSuccess.getSuccess()) jpaFavoriteCommentRepository.save(favoriteComment);

        //'성공 여부'가 설정된 responseSuccess 반환
        return favoriteResponseSuccess;
    }
}
