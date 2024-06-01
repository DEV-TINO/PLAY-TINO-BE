package com.DevTino.play_tino.favorite.service;

import com.DevTino.play_tino.favorite.Bean.Small.*;
import com.DevTino.play_tino.favorite.domain.FavoriteComment;
import com.DevTino.play_tino.favorite.domain.DTO.*;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.user.Bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FavoriteCommentService {

    SaveFavoriteCommentDAOBean saveFavoriteCommentDAOBean;
    UpdateFavoriteCommentDAOBean updateFavoriteCommentDAOBean;
    DeleteFavoriteCommentDAOBean deleteFavoriteCommentDAOBean;
    GetFavoriteCommentDAOBean getFavoriteCommentDAOBean;
    GetFavoriteCommentDAOsBean getFavoriteCommentDAOsBean;
    GetBooleanUserHeartBean getBooleanUserHeartBean;
    GetUserDAOBean getUserDAOBean;

    @Autowired
    public FavoriteCommentService(SaveFavoriteCommentDAOBean saveFavoriteCommentDAOBean, UpdateFavoriteCommentDAOBean updateFavoriteCommentDAOBean, DeleteFavoriteCommentDAOBean deleteFavoriteCommentDAOBean, GetFavoriteCommentDAOBean getFavoriteCommentDAOBean, GetFavoriteCommentDAOsBean getFavoriteCommentDAOsBean, GetBooleanUserHeartBean getBooleanUserHeartBean, GetUserDAOBean getUserDAOBean){
        this.saveFavoriteCommentDAOBean = saveFavoriteCommentDAOBean;
        this.updateFavoriteCommentDAOBean = updateFavoriteCommentDAOBean;
        this.deleteFavoriteCommentDAOBean = deleteFavoriteCommentDAOBean;
        this.getFavoriteCommentDAOBean = getFavoriteCommentDAOBean;
        this.getFavoriteCommentDAOsBean = getFavoriteCommentDAOsBean;
        this.getBooleanUserHeartBean = getBooleanUserHeartBean;
        this.getUserDAOBean = getUserDAOBean;
    }


    // 댓글 조회 (페이징)
    public ResponseFavoriteCommentsDTO readCommentAll(UUID userId, int pageNo, String criteria){

        // "heart" 또는 "time"으로 받은 criteria -> 정렬 기준 설정
        String criteriaName;
        if (criteria.equals("heart")) { criteriaName = "heartCount"; }
        else if (criteria.equals("time")) { criteriaName = "uploadTime"; }
        else return null;


        // pageNo(페이지넘버)와 criteriaName(기준)으로 페이징
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.by(Sort.Direction.DESC, criteriaName));
        Page<FavoriteComment> page = getFavoriteCommentDAOsBean.exec(pageable);

        // 유저아이디에 따른 DTOList 생성(해당 사용자가 하트를 눌렀는지도 같이 반환해줄 것)
        List<ResponseFavoriteCommentByUserDTO> responseFavoriteCommentByUserDTOList = new ArrayList<>();

        // commentList의 DAO를 DTO로 변환해 DTOList에 추가
        for(FavoriteComment favoriteComment : page){
            ResponseFavoriteCommentByUserDTO responseCommentDTO = new ResponseFavoriteCommentByUserDTO();

            responseCommentDTO.setCommentId(favoriteComment.getCommentId());
            responseCommentDTO.setUserId(favoriteComment.getUserId());
            responseCommentDTO.setUserName(getUserDAOBean.exec(favoriteComment.getUserId()).getUserName());
            responseCommentDTO.setContent(favoriteComment.getContent());
            responseCommentDTO.setHeartCount(favoriteComment.getHeartCount());
            responseCommentDTO.setUploadTime(favoriteComment.getUploadTime());

            // 해당 댓글에 사용자가 하트를 눌렀는지 여부도 저장
            Boolean checkUserHeart = getBooleanUserHeartBean.checkUserHeart(favoriteComment.getCommentId(), userId);
            responseCommentDTO.setUserHeart(checkUserHeart);

            responseFavoriteCommentByUserDTOList.add(responseCommentDTO);
        }

        // 반환할 DTO 생성 : CommmentList와 Total 수
        ResponseFavoriteCommentsDTO responseFavoriteCommentsDTO = new ResponseFavoriteCommentsDTO();
        responseFavoriteCommentsDTO.setCommentList(responseFavoriteCommentByUserDTOList);
        responseFavoriteCommentsDTO.setCommentTotal(getFavoriteCommentDAOsBean.exec().size());

        return responseFavoriteCommentsDTO;

    }

    //댓글 저장
    public FavoriteResponseSuccess createComment(RequestFavoriteCommentSaveDTO requestFavoriteCommentSaveDTO){

        //DAO를 생성해서 [PK, 하트개수, 업로드시간] 초기화
        FavoriteComment favoriteComment = new FavoriteComment();

        favoriteComment.setCommentId(UUID.randomUUID());
        favoriteComment.setHeartCount(0);
        favoriteComment.setUploadTime(LocalDateTime.now());

        //DAO에 DTO의 값(userId, Content) 넣어주기
        favoriteComment.setUserId(requestFavoriteCommentSaveDTO.getUserId());


        favoriteComment.setContent(requestFavoriteCommentSaveDTO.getContent());

        //DAO 저장
        return saveFavoriteCommentDAOBean.exec(favoriteComment);

    }

    //댓글 수정
    public FavoriteResponseSuccess updateComment(RequestFavoriteCommentUpdateDTO requestFavoriteCommentUpdateDTO){

        //DTO의 commentId로 댓글(DAO) 찾기
        FavoriteComment favoriteComment = getFavoriteCommentDAOBean.exec(requestFavoriteCommentUpdateDTO.getCommentId());

        //찾은 댓글의 내용 수정 후 저장
        return updateFavoriteCommentDAOBean.exec(favoriteComment, requestFavoriteCommentUpdateDTO);

    }
    
    //댓글 삭제
    public FavoriteResponseSuccess deleteComment(RequestFavoriteCommentDeleteDTO requestFavoriteCommentDeleteDTO){

        //id를 통해 댓글 찾기(DAO)
        FavoriteComment favoriteComment = getFavoriteCommentDAOBean.exec(requestFavoriteCommentDeleteDTO.getCommentId());

        //찾은 DAO 삭제
        return deleteFavoriteCommentDAOBean.exec(favoriteComment, requestFavoriteCommentDeleteDTO.getUserId());

    }
}
