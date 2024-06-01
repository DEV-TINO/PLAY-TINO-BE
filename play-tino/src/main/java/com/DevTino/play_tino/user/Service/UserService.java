package com.DevTino.play_tino.user.Service;


import com.DevTino.play_tino.user.Bean.GetUserBean;
import com.DevTino.play_tino.user.Domain.DTO.ResponseUserGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    GetUserBean getUserBean;

    @Autowired
    public UserService(GetUserBean getUserBean){
        this.getUserBean = getUserBean;
    }

    // 유저 조회
    public ResponseUserGetDTO read(UUID userId){
        return getUserBean.exec(userId);
    }
}
