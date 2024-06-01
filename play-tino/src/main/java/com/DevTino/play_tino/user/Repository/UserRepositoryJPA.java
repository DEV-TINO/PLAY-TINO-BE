package com.DevTino.play_tino.user.Repository;

import com.DevTino.play_tino.user.Domain.DAO.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserDAO, UUID>{

    UserDAO findByOauthId(String oauthId);
}
