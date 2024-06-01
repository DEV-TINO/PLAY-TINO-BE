package com.DevTino.play_tino.user.Bean;


import com.DevTino.play_tino.user.Bean.small.CreateUserDTOBean;
import com.DevTino.play_tino.user.Bean.small.GetUserDAOBean;
import com.DevTino.play_tino.user.Domain.DAO.UserDAO;
import com.DevTino.play_tino.user.Domain.DTO.ResponseUserGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetUserBean {

    GetUserDAOBean getUserDAOBean;
    CreateUserDTOBean createUserDTOBean;

    @Autowired
    public GetUserBean(GetUserDAOBean getUserDAOBean, CreateUserDTOBean createUserDTOBean){
        this.getUserDAOBean = getUserDAOBean;
        this.createUserDTOBean = createUserDTOBean;
    }

    // 유저 조회
    public ResponseUserGetDTO exec(UUID userId){

        // 유저 id로 DAO 조회
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // DAO -> DTO 변환 및 반환
        return createUserDTOBean.exec(userDAO);
    }
}
