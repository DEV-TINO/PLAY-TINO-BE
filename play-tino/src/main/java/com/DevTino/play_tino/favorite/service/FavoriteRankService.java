package com.DevTino.play_tino.favorite.service;

import com.DevTino.play_tino.favorite.Bean.Small.*;
import com.DevTino.play_tino.favorite.domain.DTO.RequestFavoriteRankDTO;
import com.DevTino.play_tino.favorite.domain.DTO.ResponseFavoriteRankDTO;
import com.DevTino.play_tino.favorite.domain.DTO.ResponseFavoriteRanksDTO;
import com.DevTino.play_tino.favorite.domain.Favorite;
import com.DevTino.play_tino.favorite.domain.FavoriteCount;
import com.DevTino.play_tino.favorite.domain.FavoriteRank;
import com.DevTino.play_tino.favorite.domain.FavoriteResponseSuccess;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FavoriteRankService {

    JpaFavoriteRankRepository jpaFavoriteRankRepository;
    SaveFavoriteRankDAOBean saveFavoriteRankDAOBean;
    GetFavoriteRankCountBean getFavoriteRankCountBean;
    GetFavoriteDAOsBean getFavoriteDAOsBean;
    GetFavoriteDAOBean getFavoriteDAOBean;
    SaveFavoriteCountDAOBean saveFavoriteCountDAOBean;
    GetFavoriteCountDAOsBean getFavoriteCountDAOsBean;
    GetFavoriteCountDAOBean getFavoriteCountDAOBean;

    @Autowired
    public FavoriteRankService(JpaFavoriteRankRepository jpaFavoriteRankRepository, SaveFavoriteRankDAOBean saveFavoriteRankDAOBean, GetFavoriteRankCountBean getFavoriteRankCountBean, GetFavoriteDAOsBean getFavoriteDAOsBean, GetFavoriteDAOBean getFavoriteDAOBean, SaveFavoriteCountDAOBean saveFavoriteCountDAOBean, GetFavoriteCountDAOsBean getFavoriteCountDAOsBean, GetFavoriteCountDAOBean getFavoriteCountDAOBean) {
        this.jpaFavoriteRankRepository = jpaFavoriteRankRepository;
        this.saveFavoriteRankDAOBean = saveFavoriteRankDAOBean;
        this.getFavoriteRankCountBean = getFavoriteRankCountBean;
        this.getFavoriteDAOsBean = getFavoriteDAOsBean;
        this.getFavoriteDAOBean = getFavoriteDAOBean;
        this.saveFavoriteCountDAOBean = saveFavoriteCountDAOBean;
        this.getFavoriteCountDAOsBean = getFavoriteCountDAOsBean;
        this.getFavoriteCountDAOBean = getFavoriteCountDAOBean;
    }

    // 랭킹[게임에서 1등한 Favorite] 저장
    public FavoriteResponseSuccess saveRank(RequestFavoriteRankDTO requestFavoriteRankDTO){

        // FavoriteRank(DAO) 생성, DTO를 DAO로 변환
        FavoriteRank favoriteRankDAO = new FavoriteRank();
        favoriteRankDAO.setGameId(requestFavoriteRankDTO.getGameId());
        favoriteRankDAO.setFavoriteId(requestFavoriteRankDTO.getFavoriteId());

        // favoriteCount 테이블을 favoriteId로 찾아서 count 1증가
        FavoriteCount favoriteCount = getFavoriteCountDAOBean.exec(requestFavoriteRankDTO.getFavoriteId());
        favoriteCount.setRankCount(favoriteCount.getRankCount()+1);

        // 증가한 favoriteCount 저장
        saveFavoriteCountDAOBean.exec(favoriteCount);

        // favoriteRank DAO 저장, '성공 여부'를 반환
        return saveFavoriteRankDAOBean.exec(favoriteRankDAO);

    }

    // 랭킹 조회 - 페이징
    public ResponseFavoriteRanksDTO readRankAll(Integer pageNo){

        // FavoriteCount를 rankCount, priority 내림차순 기준으로 정렬 페이징
        Sort sort = Sort.by(
                Sort.Order.desc("rankCount"),
                Sort.Order.asc("priority")
        );
        Pageable pageable = PageRequest.of(pageNo, 3, sort);
        Page<FavoriteCount> page = getFavoriteCountDAOsBean.exec(pageable);



        // 내림차순 정렬된 순서대로 (반환할) DTO(랭킹)의 리스트를 생성하고 값 설정하는 과정!
        // List<ResponseFavoriteRankDTO> favoriteRankList 생성
        List<ResponseFavoriteRankDTO> favoriteRankList = new ArrayList<>();
        Integer totalRankCount = getFavoriteRankCountBean.exec();

        // [ count : 내림차순 ] 정렬된 map에서 key 하나씩 꺼내(for)
        for(FavoriteCount favoriteCount : page) {

            UUID favoriteId = favoriteCount.getFavoriteId();
            Integer rankCount = favoriteCount.getRankCount();

            // favoriteId로 Favorite 찾아
            Favorite favorite = getFavoriteDAOBean.exec(favoriteId);

            // ResponseFavoriteRankDTO 생성 및 초기화
            ResponseFavoriteRankDTO favoriteRankDTO = new ResponseFavoriteRankDTO();
            favoriteRankDTO.setFavoriteId(favoriteId);
            favoriteRankDTO.setFavoriteImage(favorite.getFavoriteImage());
            favoriteRankDTO.setFavoriteTitle(favorite.getFavoriteTitle());
            favoriteRankDTO.setFavoriteRankCount(rankCount);
            if(totalRankCount!=0) favoriteRankDTO.setFavoriteRankPercentage(((double)rankCount/(double)totalRankCount)*100);
            else favoriteRankDTO.setFavoriteRankPercentage(0.0);

            // favoriteRankList에 저장(add)
            favoriteRankList.add(favoriteRankDTO);
        }

        // 응답할 DTO 설정(Rank List와 totalCount)
        ResponseFavoriteRanksDTO responseFavoriteRanksDTO = new ResponseFavoriteRanksDTO();
        responseFavoriteRanksDTO.setRankList(favoriteRankList);
        responseFavoriteRanksDTO.setFavoriteRankTotal(getFavoriteDAOsBean.exec().size());

        //return favoriteRankList;
        return responseFavoriteRanksDTO;
    }



}
