package com.DevTino.play_tino.user.Bean.small;

import com.DevTino.play_tino.user.Domain.DAO.UserDAO;
import com.DevTino.play_tino.user.Repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveUserDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public SaveUserDAOBean(UserRepositoryJPA userRepositoryJPA){
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 유저 저장
    public void exec(UserDAO userDAO){
        userRepositoryJPA.save(userDAO);
    }
}
