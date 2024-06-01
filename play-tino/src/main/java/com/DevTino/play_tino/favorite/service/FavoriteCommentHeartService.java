package com.DevTino.play_tino.favorite.service;

import com.DevTino.play_tino.favorite.Bean.Small.*;
import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import com.DevTino.play_tino.favorite.domain.FavoriteCommentHeart;
import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteCommentHeartDeleteDTO;
import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteCommentHeartSaveDTO;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FavoriteCommentHeartService {

    GetFavoriteCommentDAOBean getFavoriteCommentDAOBean;
    UpdateFavoriteCommentHeartCountDAOBean updateFavoriteCommentHeartCountDAOBean;
    SaveFavoriteCommentDAOBean saveFavoriteCommentDAOBean;
    SaveFavoriteCommentHeartDAOBean saveFavoriteCommentHeartDAOBean;
    DeleteFavoriteCommentHeartDAOBean deleteFavoriteCommentHeartDAOBean;
    GetFavoriteCommentHeartDAOBean getFavoriteCommentHeartDAOBean;


    @Autowired
    public FavoriteCommentHeartService(GetFavoriteCommentDAOBean getFavoriteCommentDAOBean, UpdateFavoriteCommentHeartCountDAOBean updateFavoriteCommentHeartCountDAOBean, SaveFavoriteCommentDAOBean saveFavoriteCommentDAOBean, SaveFavoriteCommentHeartDAOBean saveFavoriteCommentHeartDAOBean, DeleteFavoriteCommentHeartDAOBean deleteFavoriteCommentHeartDAOBean, GetFavoriteCommentHeartDAOBean getFavoriteCommentHeartDAOBean){
        this.getFavoriteCommentDAOBean = getFavoriteCommentDAOBean;
        this.updateFavoriteCommentHeartCountDAOBean = updateFavoriteCommentHeartCountDAOBean;
        this.saveFavoriteCommentDAOBean = saveFavoriteCommentDAOBean;
        this.saveFavoriteCommentHeartDAOBean = saveFavoriteCommentHeartDAOBean;
        this.deleteFavoriteCommentHeartDAOBean = deleteFavoriteCommentHeartDAOBean;
        this.getFavoriteCommentHeartDAOBean = getFavoriteCommentHeartDAOBean;
    }

    //댓글좋아요 저장
    public FavoriteResponseSuccess createHeart(RequestFavoriteCommentHeartSaveDTO requestFavoriteCommentHeartSaveDTO){

        //commentHeartSaveBean.exec();
        // 기존에 있는 좋아요인지 중복확인
        UUID commentId = requestFavoriteCommentHeartSaveDTO.getCommentId();
        UUID userId = requestFavoriteCommentHeartSaveDTO.getUserId();

        if(getFavoriteCommentHeartDAOBean.exec(commentId, userId) != null){
            FavoriteResponseSuccess favoriteResponseSuccess = new FavoriteResponseSuccess();
            favoriteResponseSuccess.setSuccess(false);
            return favoriteResponseSuccess;
        }

        // 저장할 댓글좋아요 DAO 생성 및 값 초기화
        FavoriteCommentHeart favoriteCommentHeart = new FavoriteCommentHeart();
        favoriteCommentHeart.setCommentHeartId(UUID.randomUUID());
        favoriteCommentHeart.setCommentId(requestFavoriteCommentHeartSaveDTO.getCommentId());
        favoriteCommentHeart.setUserId(requestFavoriteCommentHeartSaveDTO.getUserId());

        // 댓글 좋아요 생성으로 인한 댓글 좋아요 갯수 증가(해당 댓글 DAO를 업데이트)
        FavoriteComment favoriteComment = getFavoriteCommentDAOBean.exec(requestFavoriteCommentHeartSaveDTO.getCommentId());
        updateFavoriteCommentHeartCountDAOBean.heartCountUp(favoriteComment);

        // 좋아요 수가 변경된 댓글 DAO 저장[업데이트]
        saveFavoriteCommentDAOBean.exec(favoriteComment);

        // 댓글좋아요 DAO 저장
        return saveFavoriteCommentHeartDAOBean.exec(favoriteCommentHeart);
    }

    //댓글좋아요 삭제
    public FavoriteResponseSuccess deleteHeart(RequestFavoriteCommentHeartDeleteDTO requestFavoriteCommentHeartDeleteDTO){

        //userId와 commentId로 댓글좋아요(DAO) 찾기
        FavoriteCommentHeart favoriteCommentHeart
                = getFavoriteCommentHeartDAOBean.exec(requestFavoriteCommentHeartDeleteDTO.getCommentId(), requestFavoriteCommentHeartDeleteDTO.getUserId());

        // 해당하는 댓글 좋아요가 있는지 확인 (없으면 '실패' 반환)
        if(favoriteCommentHeart == null){
            FavoriteResponseSuccess favoriteResponseSuccess = new FavoriteResponseSuccess();
            favoriteResponseSuccess.setSuccess(false);
            return favoriteResponseSuccess;
        }

        // 댓글 좋아요 삭제로 인한 댓글 좋아요 개수 감소(해당 댓글 DAO를 업데이트)
        FavoriteComment favoriteComment = getFavoriteCommentDAOBean.exec(requestFavoriteCommentHeartDeleteDTO.getCommentId());
        updateFavoriteCommentHeartCountDAOBean.heartCountDown(favoriteComment);

        // 좋아요 수가 변경된 댓글 DAO 저장[업데이트]
        saveFavoriteCommentDAOBean.exec(favoriteComment);

        // 댓글좋아요 DAO 삭제
        //jpaCommentHeartRepository.delete(commentHeart);
        return deleteFavoriteCommentHeartDAOBean.exec(favoriteCommentHeart);
    }
}
