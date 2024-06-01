package com.DevTino.play_tino.favorite.service;

import com.DevTino.play_tino.favorite.Bean.Small.GetFavoriteGameDAOBean;
import com.DevTino.play_tino.favorite.Bean.Small.GetRandomFavoriteDAOsBean;
import com.DevTino.play_tino.favorite.Bean.Small.SaveFavoriteGameDAOBean;
import com.DevTino.play_tino.favorite.domain.DTO.ResponseFavoriteGameDTO;
import com.DevTino.play_tino.favorite.domain.FavoriteGame;
import com.DevTino.play_tino.favorite.repository.JpaFavoriteGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FavoriteGameService {

    JpaFavoriteGameRepository jpaFavoriteGameRepository;
    GetRandomFavoriteDAOsBean getRandomFavoriteDAOsBean;
    SaveFavoriteGameDAOBean saveFavoriteGameDAOBean;
    GetFavoriteGameDAOBean getFavoriteGameDAOBean;

    @Autowired
    public FavoriteGameService(JpaFavoriteGameRepository jpaFavoriteGameRepository, GetRandomFavoriteDAOsBean getRandomFavoriteDAOsBean, SaveFavoriteGameDAOBean saveFavoriteGameDAOBean, GetFavoriteGameDAOBean getFavoriteGameDAOBean){
        this.jpaFavoriteGameRepository = jpaFavoriteGameRepository;
        this.getRandomFavoriteDAOsBean = getRandomFavoriteDAOsBean;
        this.saveFavoriteGameDAOBean = saveFavoriteGameDAOBean;
        this.getFavoriteGameDAOBean = getFavoriteGameDAOBean;
    }

    // 새 게임 생성 : 게임Id 생성과 조회 및 16개 컨텐츠 조회
    public ResponseFavoriteGameDTO createGame(UUID userId, UUID gameId){

        // gameId 가 없을경우 있을경우를 나눈다
        
        // gameId가 있을 경우 : 기존에 있던 게임을 찾아서 반환
        if(gameId != null){
            
            // favoriteGame (DAO) 찾기 : getFavoriteGameDAOBean
            FavoriteGame favoriteGame = getFavoriteGameDAOBean.exec(gameId);
            
            // 반환할 ResponseFavoriteGameDTO 생성
            ResponseFavoriteGameDTO responseFavoriteGameDTO = new ResponseFavoriteGameDTO();
            
            // DTO 값 설정
            responseFavoriteGameDTO.setGameId(gameId);
            responseFavoriteGameDTO.setFavoriteList(favoriteGame.getFavoriteList());

            // DTO 반환
            return  responseFavoriteGameDTO;
        }

        // gameId가 없을 경우
        
        // FavoriteGame DAO 생성 -> gameId, userId, favoriteList 값 초기화
        FavoriteGame favoriteGame = new FavoriteGame();
        favoriteGame.setGameId(UUID.randomUUID());
        favoriteGame.setUserId(userId);

        // 16개 컨텐츠 랜덤 선택
        favoriteGame.setFavoriteList(getRandomFavoriteDAOsBean.exec());

        // DAO 저장
        saveFavoriteGameDAOBean.exec(favoriteGame);

        // ResponseFavoriteGameDTO 생성
        ResponseFavoriteGameDTO responseFavoriteGameDTO = new ResponseFavoriteGameDTO();

        // gameId와 favoriteList 값 설정
        responseFavoriteGameDTO.setGameId(favoriteGame.getGameId());
        responseFavoriteGameDTO.setFavoriteList(favoriteGame.getFavoriteList());

        // 생성된 ResponseFavoriteGameDTO 반환
        return responseFavoriteGameDTO;
    }
}
