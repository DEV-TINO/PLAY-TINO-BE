package com.DevTino.play_tino.timer.service;

import com.DevTino.play_tino.timer.bean.*;
import com.DevTino.play_tino.timer.domain.DTO.RequestTimerSaveDTO;
import com.DevTino.play_tino.timer.domain.DTO.ResponseSaveTimerRankDTO;
import com.DevTino.play_tino.timer.domain.DTO.ResponseTimerRanksDTO;
import com.DevTino.play_tino.timer.domain.DTO.ResponseTimerReadAllDTO;
import com.DevTino.play_tino.timer.domain.entity.Timer;
import com.DevTino.play_tino.timer.domain.entity.TimerRank;
import com.DevTino.play_tino.timer.repository.JpaTimerRankRepository;
import com.DevTino.play_tino.timer.repository.JpaTimerRepository;
import com.DevTino.play_tino.user.Bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TimerRankService {

    JpaTimerRepository jpaTimerRepository;
    JpaTimerRankRepository jpaTimerRankRepository;
    GetTimerDAOsBean getTimerDAOsBean;
    SaveTimerErrorRangeBean saveTimerErrorRangeBean;
    SaveTimerRankDAOBean saveTimerRankDAOBean;
    GetUserDAOBean getUserDAOBean;

    @Autowired
    public TimerRankService(JpaTimerRepository jpaTimerRepository, JpaTimerRankRepository jpaTimerRankRepository, GetTimerDAOsBean getTimerDAOsBean, SaveTimerErrorRangeBean saveTimerErrorRangeBean, SaveTimerRankDAOBean saveTimerRankDAOBean, GetUserDAOBean getUserDAOBean){
        this.jpaTimerRepository = jpaTimerRepository;
        this.jpaTimerRankRepository = jpaTimerRankRepository;
        this.getTimerDAOsBean = getTimerDAOsBean;
        this.saveTimerErrorRangeBean = saveTimerErrorRangeBean;
        this.saveTimerRankDAOBean = saveTimerRankDAOBean;
        this.getUserDAOBean = getUserDAOBean;
    }

    // 랭킹 저장
    public ResponseSaveTimerRankDTO save(RequestTimerSaveDTO requestTimerSaveDTO){

        ResponseSaveTimerRankDTO responseSaveTimerRankDTO = new ResponseSaveTimerRankDTO();

        // DTO를 통해 DAO 생성
        Timer timer = jpaTimerRepository.findByGameIdAndUserId(requestTimerSaveDTO.getGameId(), requestTimerSaveDTO.getUserId());


        // DAO 값 초기화
        timer.setStopTime(requestTimerSaveDTO.getStopTime());
        //timer.setUploadTime(LocalDateTime.now());
        timer.setUploadTime(LocalDateTime.now().plusHours(9));
        timer.setErrorRange(saveTimerErrorRangeBean.exec(requestTimerSaveDTO));

        // 에러 처리
        if (jpaTimerRankRepository.findByTimerIdAndUserId(requestTimerSaveDTO.getGameId(), requestTimerSaveDTO.getUserId()) != null)
            return null;

        // timer에 에러 범위 내림차순으로 100개의 DAO 가져옴
        List<Timer> timerList = jpaTimerRepository.findTop100ByOrderByErrorRangeAsc();

        // TimerRank 새로 생성 해줌
        TimerRank timerRank = new TimerRank();

        // TimerRankDAO 저장
        timerRank.setTimerRankId(UUID.randomUUID());
        timerRank.setTimerId(requestTimerSaveDTO.getGameId());
        timerRank.setUserId(requestTimerSaveDTO.getUserId());

        // 랭킹 저장을 할때 화면에 랭킹이 등록되었습니다.
        // 랭킹을 100개로 짜르는데 얘가 100등안에 드는지 여부를 판단해서 줘야함
        if(timerList.size() < 100) {
            jpaTimerRepository.save(timer);
            responseSaveTimerRankDTO = saveTimerRankDAOBean.exec(timerRank);
        }
        else {
            double newErrorRange = Double.parseDouble(saveTimerErrorRangeBean.exec(requestTimerSaveDTO));
            double oldErrorRange = Double.parseDouble(timerList.get(99).getErrorRange());

            if (newErrorRange < oldErrorRange){
                jpaTimerRepository.save(timer);
                responseSaveTimerRankDTO = saveTimerRankDAOBean.exec(timerRank);
            } else {
                responseSaveTimerRankDTO.setRankIn(false);
                responseSaveTimerRankDTO.setSuccess(false);
            }
        }

        return responseSaveTimerRankDTO;
    }

    // 랭킹 전체 조회
    public ResponseTimerRanksDTO readAll(Pageable pageable){

        // timerId가 존재하는 timerList 생성
        List<Timer> timerPage = getTimerDAOsBean.exec(pageable);

        // Calculate the start and end indices for the sublist
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), timerPage.size());

        if (start >= timerPage.size()){
            return null;
        }

        List<Timer> timerRankList = timerPage.subList(start, end);

        //DTOList생성
        List<ResponseTimerReadAllDTO> responseTimerReadAllDTOList = new ArrayList<>();

        // DAO를 이용해 DTOList 추가
        for(Timer timer : timerRankList){
            ResponseTimerReadAllDTO responseTimerReadAllDTO = new ResponseTimerReadAllDTO();

            responseTimerReadAllDTO.setTimerRankId(UUID.randomUUID());
            responseTimerReadAllDTO.setErrorRange(timer.getErrorRange());
            responseTimerReadAllDTO.setUserId(timer.getUserId());
            responseTimerReadAllDTO.setTargetTime(timer.getTargetTime());
            responseTimerReadAllDTO.setUserName(getUserDAOBean.exec(timer.getUserId()).getUserName());
            responseTimerReadAllDTO.setStopTime(timer.getStopTime());
            responseTimerReadAllDTO.setCreateTime(timer.getCreateTime());
            responseTimerReadAllDTO.setUploadTime(timer.getUploadTime());

            responseTimerReadAllDTOList.add(responseTimerReadAllDTO);
        }

        ResponseTimerRanksDTO responseTimerRanksDTO = new ResponseTimerRanksDTO();
        responseTimerRanksDTO.setTimerList(responseTimerReadAllDTOList);

        responseTimerRanksDTO.setTimerTotal(timerPage.size());

        // 생성한 responseTimerReadAllDTOList 반환
        return responseTimerRanksDTO;
    }
}