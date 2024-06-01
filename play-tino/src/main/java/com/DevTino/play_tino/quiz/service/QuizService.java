package com.DevTino.play_tino.quiz.service;

import com.DevTino.play_tino.quiz.Bean.*;
import com.DevTino.play_tino.quiz.domain.DTO.RequestQuizRankSaveDTO;
import com.DevTino.play_tino.quiz.domain.DTO.ResponseQuizGameDTO;
import com.DevTino.play_tino.quiz.domain.DTO.ResponseQuizRankDTO;
import com.DevTino.play_tino.quiz.domain.DTO.ResponseQuizRanksDTO;
import com.DevTino.play_tino.quiz.domain.Quiz;
import com.DevTino.play_tino.quiz.domain.QuizGame;
import com.DevTino.play_tino.quiz.domain.QuizRank;
import com.DevTino.play_tino.user.Bean.small.GetUserDAOBean;
import com.DevTino.play_tino.user.Domain.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuizService {

    GetQuizDAOBean getQuizDAOBean;
    SaveQuizGameDAOBean saveQuizGameDAOBean;
    SaveQuizRankDAOBean saveQuizRankDAOBean;
    GetQuizGameDAOBean getQuizGameDAOBean;
    GetQuizRankDAOBean getQuizRankDAOBean;
    QuizConvertListToString quizConvertListToString;
    GetQuizRankCountDAOBean getQuizRankCountDAOBean;
    CheckTop100QuizRankingDAOBean checkTop100QuizRankingDAOBean;
    GetUserDAOBean getUserDAOBean;

    @Autowired
    public QuizService(GetUserDAOBean getUserDAOBean, GetQuizDAOBean getQuizDAOBean, SaveQuizGameDAOBean saveQuizGameDAOBean, SaveQuizRankDAOBean saveQuizRankDAOBean, GetQuizGameDAOBean getQuizGameDAOBean, GetQuizRankDAOBean getQuizRankDAOBean, QuizConvertListToString quizConvertListToString, GetQuizRankCountDAOBean getQuizRankCountDAOBean, CheckTop100QuizRankingDAOBean checkTop100QuizRankingDAOBean) {
        this.getQuizDAOBean = getQuizDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.saveQuizGameDAOBean = saveQuizGameDAOBean;
        this.saveQuizRankDAOBean = saveQuizRankDAOBean;
        this.getQuizGameDAOBean = getQuizGameDAOBean;
        this.getQuizRankDAOBean = getQuizRankDAOBean;
        this.quizConvertListToString = quizConvertListToString;
        this.getQuizRankCountDAOBean = getQuizRankCountDAOBean;
        this.checkTop100QuizRankingDAOBean = checkTop100QuizRankingDAOBean;
    }


    // 게임 들어갈 때 문제 ID 생성해주고, Quiz 리스트를 반환해줌
    public ResponseQuizGameDTO readQuiz(UUID gameId, UUID userId){
        //새로고침 할때(게임 아이디가 존재)
        if (gameId != null){
            //gameId와 userId를 통해 QuizGame 객체를 찾는다
            QuizGame quizGame = getQuizGameDAOBean.exec(gameId, userId);

            // DTO 객체를 만들고 찾은 DAO 객체로 초기화
            ResponseQuizGameDTO responseQuizGameDTO = new ResponseQuizGameDTO();

            responseQuizGameDTO.setGameId(quizGame.getGameId());
            responseQuizGameDTO.setUserId(quizGame.getUserId());
            responseQuizGameDTO.setResponseQuizList(quizGame.getQuizList());

            //DTO 객체를 반환
            return responseQuizGameDTO;
        }

        //퀴즈 10개를 랜덤으로 저장
        List<Quiz> QuizList = getQuizDAOBean.exec();

        // QuizGame 객체를 만들고 초기화
        QuizGame quizGame = new QuizGame();
        quizGame.setUserId(userId);

        //List인 퀴즈들을 String으로 변환
        String stringQuizList = quizConvertListToString.exec(QuizList);

        quizGame.setQuizList(stringQuizList);
        quizGame.setCreateAt(LocalDateTime.now());

        saveQuizGameDAOBean.exec(quizGame);

        // ResponseQuizGameDTO를 만들고 setter로 저장시키고 반환하면 끝
        ResponseQuizGameDTO responseQuizGameDTO = new ResponseQuizGameDTO();
        responseQuizGameDTO.setGameId(quizGame.getGameId());
        responseQuizGameDTO.setUserId(quizGame.getUserId());
        responseQuizGameDTO.setResponseQuizList(stringQuizList);

        return responseQuizGameDTO;
    }




    //랭킹 저장하기
    public Map<String, String> saveQuizRank(RequestQuizRankSaveDTO requestQuizRankDTO) {

        Map<String, String> map = new HashMap<>();

        //gameId와 userId를 통해 이미 랭킹에 저장되어 있으면 null 값 반환
        if(getQuizRankDAOBean.exec(requestQuizRankDTO.getGameId(), requestQuizRankDTO.getUserId()) != null)
            return null;

        //랭킹 100등까지의 값을 가져온다
        List<QuizRank> quizRankList = checkTop100QuizRankingDAOBean.exec();

        //랭킹이 100개가 안될시에 map에 true값을 넣는다
        if(quizRankList.size() < 100) {
            map.put("rankIn", "true");
        }

        //랭킹이 100개가 넘을 시 100등 값을 통해 랭킹이 100등안에 드는지 확인한다
        else {
            Integer allCorrect = quizRankList.get(99).getAllCorrect();
            Integer getAllCorrect = requestQuizRankDTO.getCommonsenseCorrect() + requestQuizRankDTO.getNonsenseCorrect();
            if (getAllCorrect > allCorrect){
                map.put("rankIn", "true");}
            else{
                map.put("rankIn", "false");}
        }


        //gameId와 userId로 quizGame 객체 찾기
        QuizGame quizGame = getQuizGameDAOBean.exec(requestQuizRankDTO.getGameId(), requestQuizRankDTO.getUserId());

        // QuizRank 객체 생성 및 초기화
        QuizRank quizRank = new QuizRank();

        quizRank.setGameId(requestQuizRankDTO.getGameId());
        quizRank.setUserId(requestQuizRankDTO.getUserId());
        quizRank.setUpdateAt(LocalDateTime.now());
        quizRank.setNonsenseCorrect(requestQuizRankDTO.getNonsenseCorrect());
        quizRank.setCommonsenseCorrect(requestQuizRankDTO.getCommonsenseCorrect());

        //상식, 넌센스 맞춘 개수의 합
        quizRank.setAllCorrect(requestQuizRankDTO.getNonsenseCorrect() + requestQuizRankDTO.getCommonsenseCorrect());

        //quizRank를 repository에 저장
        saveQuizRankDAOBean.exec(quizRank);

        map.put("gameId", quizGame.getGameId().toString());

        //키값 반환
        return map;
    }

    // 랭킹 전체 조회
    public ResponseQuizRanksDTO readQuizRank(int page, int size) {
        // QuizRank 리스트를 맞춘개수 내림차순으로 페이징(5개씩)/정렬해 넣어둠
        Pageable pageable = PageRequest.of(page, size);

        Integer rankCount = getQuizRankCountDAOBean.exec();

        List<QuizRank> quizRanks = checkTop100QuizRankingDAOBean.exec();

        // Calculate the start and end indices for the sublist
        int start = (int) pageable.getOffset();

        int end = Math.min((start + pageable.getPageSize()), quizRanks.size());

        if (start > rankCount){
            return null;
        }

        // Extract the sublist based on the start and end indices
        List<QuizRank> quizRankList = quizRanks.subList(start, end);

       //Page<QuizRank> quizRankList = new PageImpl<>(quizRanks, pageable, quizRanks.size());
        //DTO 리스트 생성
        List<ResponseQuizRankDTO> responseQuizRankDTOList = new ArrayList<>();

        //for문을 통해 각 QuizRank객체를 DTO 리스트에 저장해주는 로직
        for (QuizRank quizRank : quizRankList) {
            //userId를 통해 user 객체 찾기
            UserDAO userDAO = getUserDAOBean.exec(quizRank.getUserId());

            //각 QuizRank를 객체를 담아줄 DTO 객체를 생성하고 초기화
            ResponseQuizRankDTO responseQuizRankDTO = new ResponseQuizRankDTO();
            responseQuizRankDTO.setQuizRankId(quizRank.getQuizRankId());
            responseQuizRankDTO.setNonsenseCorrect(quizRank.getNonsenseCorrect());
            responseQuizRankDTO.setCommonsenseCorrect(quizRank.getCommonsenseCorrect());
            responseQuizRankDTO.setAllCorrect(quizRank.getAllCorrect());

            responseQuizRankDTO.setUserName(userDAO.getUserName());

            //DTO리스트에 각 DTO 저장
            responseQuizRankDTOList.add(responseQuizRankDTO);
        }

        //랭킹 수와 페이지 단위로 묶인 5개 데이터를 담아줄 DTO 생성
        ResponseQuizRanksDTO responseQuizRanksDTO = new ResponseQuizRanksDTO();
        //전체 랭킹 수 계산
        responseQuizRanksDTO.setQuizTotal(getQuizRankCountDAOBean.exec());
        //페이지 단위로 묶인 5개의 데이터
        responseQuizRanksDTO.setQuizRankList(responseQuizRankDTOList);

        //DTO 리스트 반환
        return responseQuizRanksDTO;
    }

}
