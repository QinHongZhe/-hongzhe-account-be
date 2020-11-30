package com.p.v2iserver.account.controller;

import com.p.v2iserver.account.entitys.pojo.TokenDTO;
import com.p.v2iserver.account.service.TokenService;
import com.p.v2iserver.account.utils.CheckParamUtils;
import com.p.v2iserver.account.utils.NResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-10 13:21
 **/
@RestController
@RequestMapping(value = {"/token", "/v1/account"})
@Api(value = "Token接口", tags = "TokenController", description = "Token接口相关")
public class TokenController {

    @Autowired
    TokenService tokenService;

    /**
     *
     * TODO 获取Token
     * @author Pactera
     * @date 2020-11-16 14:33:36
     * @param: tokenDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/setToken", method = RequestMethod.POST)
    @ResponseBody
    public NResult setToken(@RequestBody @Valid TokenDTO tokenDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = CheckParamUtils.checkDTO(tokenDTO, results);
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = tokenService.setToken(tokenDTO);
        return nResult;
    }
}
