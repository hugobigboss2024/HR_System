package com.hrm.hrm_backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    /*
    生成 JWT (1)
    驗證 JWT 
    */
   @Value("${jwt.secret:MySuperSecretKeyForJwtSigning1234567890!}")
   private String secretKey; // 讀取金鑰

   @Value("${jwt.expiration:864000000}")
    private long expirationTime; // Token 過期時間

    private SecretKey getSigningKey(){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // (1)
    public String generateToken(Long userId, String userName){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("userName", userName);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(now).setExpiration(expiryDate).signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    // Token中解析出所有的Claims(Payload內容)
    public Claims parseToken(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    // 驗證 Token 是否有效
    public boolean validateToken(String token){
        try{
            Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }

    // Token提取Username
    public String getUsernameFromToken(String token){
        return parseToken(token).getSubject();
    }

    // Token提取UserId
    public Long getUserIdFromToken(String token){
        Claims claims = parseToken(token);
        return claims.get("userId", Long.class);
    }

}
