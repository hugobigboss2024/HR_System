package com.hrm.hrm_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrm.hrm_backend.dto.LoginRequest;
import com.hrm.hrm_backend.dto.LoginResponse;
import com.hrm.hrm_backend.dto.RegisterDTO;
import com.hrm.hrm_backend.dto.Result;
import com.hrm.hrm_backend.entity.SysUser;
import com.hrm.hrm_backend.service.SysUserService;
import com.hrm.hrm_backend.utils.JwtUtils;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody RegisterDTO registerDTO){
        // 註冊
        sysUserService.register(registerDTO);
        return Result.success("register is successful");
    }
    

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 驗證帳號密碼
        SysUser user = sysUserService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "帳號或密碼錯誤");
            return Result.error("Invalid username or password");
        }

        // 生成JWT
        String token = jwtUtils.generateToken(user.getUserId(), user.getUserName());

        /*
        // 封裝Response
        LoginResponse response = new LoginResponse(token, user.getUserId(), user.getUserName());
        return ResponseEntity.ok(response);
        */
        /*
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("userId", user.getUserId());
        data.put("username", user.getUserName());
        data.put("realName", user.getRealName());
        return Result.success(data);
        */
        LoginResponse response = new LoginResponse(token, user.getUserId(), user.getUserName(), user.getRealName());

        return Result.success(response);
    }

    // 取得當前登入者資訊驗證Token
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        // 驗證Token
        if (!jwtUtils.validateToken(token)) {
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
