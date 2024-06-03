package com.DevTino.play_tino.user.Controller;

import com.DevTino.play_tino.user.Domain.DTO.ResponseUserGetDTO;
import com.DevTino.play_tino.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
//@CrossOrigin(origins = "https://play-tino.com") // 배포
@CrossOrigin("*") // 로컬
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // 유저 저장은 로그인시 하도록 설정
    // 코드 x

    // 유저 조회
    @GetMapping("/user/{userId}")
    public ResponseUserGetDTO read(@PathVariable UUID userId){
        return userService.read(userId);
    }
}
