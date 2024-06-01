package com.DevTino.play_tino.user.Controller;

import com.DevTino.play_tino.user.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    // 로그인
    @GetMapping("/login/oauth2/code/{registrationId}")
    public ResponseEntity<Map<String, Object>> googleLogin(@RequestParam String code, @PathVariable String registrationId) {

        try {
            UUID userId = loginService.socialLogin(code, registrationId);

            // HTTP 상태 반환
            HttpStatus httpStatus = (userId != null) ? HttpStatus.PERMANENT_REDIRECT : HttpStatus.INTERNAL_SERVER_ERROR;

            String redirectUrl = "https://play-tino.com?userId=" + userId;

            // 헤더 추가 및 Redirect:
            HttpHeaders headers = new HttpHeaders();

            headers.setLocation(URI.create(redirectUrl));

            return ResponseEntity.status(httpStatus).headers(headers).body(new HashMap<>());

        } catch (Exception e) {
            // 예외가 발생한 경우 로깅
            e.printStackTrace(); // 에러 내용 로깅

            // 에러 응답 반환
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("message", "Internal Server Error");
            errorMap.put("detail", e.getMessage()); // 예외 메시지를 추가로 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
        }
    }

}
