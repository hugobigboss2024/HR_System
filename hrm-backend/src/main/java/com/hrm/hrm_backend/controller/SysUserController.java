package com.hrm.hrm_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrm.hrm_backend.dto.LoginRequest;
import com.hrm.hrm_backend.dto.LoginResponse;
import com.hrm.hrm_backend.entity.SysUser;
import com.hrm.hrm_backend.service.SysUserService;
import com.hrm.hrm_backend.utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        // 驗證帳號密碼
         SysUser user = SysUserService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if(user == null){
            Map<String, String> error = new HashMap<>();
            error.put("message", "user name or password is wrong!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        // 生成JWT
        String token = jwtUtils.generateToken(user.getUserId(), user.getUserName());

        // 封裝Response
        LoginResponse response = new LoginResponse(token, user.getUserId(), user.getUserName());

        return ResponseEntity.ok(response);
    }

    // 取得當前登入者資訊驗證Token
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        // 驗證Token
        if(!jwtUtils.validateToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        // 取得使用者資訊
        Long userId = jwtUtils.getUserIdFromToken(token);
        String username = jwtUtils.getUsernameFromToken(token);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", userId);
        userInfo.put("username", username);

        return ResponseEntity.ok(userInfo);
    }
}
