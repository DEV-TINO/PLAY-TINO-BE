package com.DevTino.play_tino.user.Bean.small;

import com.DevTino.play_tino.user.Domain.DAO.UserDAO;
import com.DevTino.play_tino.user.Domain.DTO.ResponseUserGetDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateUserDTOBean {

    // 유저 DTO 변환
    public ResponseUserGetDTO exec(UserDAO userDAO){
        return ResponseUserGetDTO.builder()
                .userId(userDAO.getUserId())
                .userName(userDAO.getUserName())
                .build();
    }
}
