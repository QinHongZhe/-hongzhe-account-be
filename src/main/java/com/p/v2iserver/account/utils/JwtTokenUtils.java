package com.p.v2iserver.account.utils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JwtTokenUtils {
    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;
    //token秘钥
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";

    public static String createToken (String userName,String clientType,boolean isRememberMe){
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+expiration*1000);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("userName",userName)
                    .withClaim("clientType",clientType).withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return token;
    }

    public static DecodedJWT verify(String token){
        DecodedJWT jwt;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            jwt= verifier.verify(token);
            return jwt;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public static String getUsername(String token){
        String username="";
        if(null!=verify(token)){
            Map<String, Claim> tokenObj = verify(token).getClaims();
            username = tokenObj.get("userName").asString();
        }
        return username;
    }


    public static Object tokenTimeOut(String token, String methodName){
        String message = "";
        String username = JwtTokenUtils.getUsername(token);
        if(null==username || "".equals(username)){
            message = "当前请求【"+methodName+"】token过期！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SESSION_FAIL_CODE.getCode(),
                    NStatusMessage.SystemStatus.SESSION_FAIL_CODE.getMessage(),null);
        }
        return username;
    }


    public static void main(String[] args) {
        System.out.println(getUsername("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnRUeXBlIjoiLTEiLCJleHAiOjE1OTk1NjA1MjN9.kyERfxKf1Mx3VZhEKzbi3D6K9nRe6zNqpcJ6rYb3MNY"));
    }

}
