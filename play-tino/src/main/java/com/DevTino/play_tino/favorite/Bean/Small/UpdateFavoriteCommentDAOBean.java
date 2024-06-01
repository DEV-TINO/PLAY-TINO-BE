package com.DevTino.play_tino.favorite.Bean.Small;

import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteCommentUpdateDTO;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.others.FavoriteCheckUserCommentBean;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateFavoriteCommentDAOBean {
    JpaFavoriteCommentRepository jpaFavoriteCommentRepository;
    FavoriteCheckUserCommentBean favoriteCheckUserCommentBean;

    @Autowired
    public UpdateFavoriteCommentDAOBean(JpaFavoriteCommentRepository jpaFavoriteCommentRepository, FavoriteCheckUserCommentBean favoriteCheckUserCommentBean){
        this.jpaFavoriteCommentRepository = jpaFavoriteCommentRepository;
        this.favoriteCheckUserCommentBean = favoriteCheckUserCommentBean;
    }

    //댓글 수정
    public FavoriteResponseSuccess exec(FavoriteComment favoriteComment, RequestFavoriteCommentUpdateDTO requestFavoriteCommentUpdateDTO){
        //responseSuccess 생성
        FavoriteResponseSuccess favoriteResponseSuccess = new FavoriteResponseSuccess();

        //댓글이 유저가 작성한 댓글이 맞는지 확인 -> 결과에 따라 '성공 여부' 설정
        if(favoriteCheckUserCommentBean.exec(favoriteComment, requestFavoriteCommentUpdateDTO.getUserId()))
            favoriteResponseSuccess.setSuccess(true);
        else favoriteResponseSuccess.setSuccess(false);

        //성공한 경우만 DAO 수정해서 저장
        if((favoriteResponseSuccess.getSuccess())){

            favoriteComment.setContent(requestFavoriteCommentUpdateDTO.getContent());
            jpaFavoriteCommentRepository.save(favoriteComment);
        }

        //'성공 여부'가 설정된 responseSuccess 반환
        return favoriteResponseSuccess;
    }
}
