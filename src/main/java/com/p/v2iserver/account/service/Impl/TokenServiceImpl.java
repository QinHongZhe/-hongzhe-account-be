package com.p.v2iserver.account.service.Impl;

import com.github.pagehelper.PageInfo;
import com.p.v2iserver.account.entitys.pojo.TokenDTO;
import com.p.v2iserver.account.entitys.pojo.TokenVO;
import com.p.v2iserver.account.service.TokenService;
import com.p.v2iserver.account.utils.JwtTokenUtils;
import com.p.v2iserver.account.utils.NResult;
import com.p.v2iserver.account.utils.NResultUtil;
import com.p.v2iserver.account.utils.NStatusMessage;
import io.jsonwebtoken.Jwt;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-10 13:26
 **/
@Service
@Log4j2
public class TokenServiceImpl implements TokenService {

    /**
     *
     * TODO 获取token
     * @author Pactera
     * @date 2020-11-16 14:49:28
     * @param: tokenDTO
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult setToken(TokenDTO tokenDTO) {
        String message ;
        String username = tokenDTO.getUsername();
        String token = JwtTokenUtils.createToken(username,tokenDTO.getClientType().toString(),tokenDTO.isRemeberMe);
        if(""==token){
            message = "当前用户:【" + username + "】,该方法【" + "setToken" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "setToken" + "】调用成功！！！";
        log.debug(message);
        TokenVO tokenVO = new TokenVO();
        tokenVO.setToken(token);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,tokenVO);
    }
}
