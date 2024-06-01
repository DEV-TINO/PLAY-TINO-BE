package com.DevTino.play_tino.user.Bean.small;

import com.DevTino.play_tino.user.Domain.DAO.UserDAO;
import com.DevTino.play_tino.user.Repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetUserDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public GetUserDAOBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 유저 id로 조회
    public UserDAO exec(UUID userId){
        return userRepositoryJPA.findById(userId).orElse(null);
    }

    // 유저 oauthId로 조회
    public UserDAO exec(String oauthId){
        return userRepositoryJPA.findByOauthId(oauthId);
    }
}
