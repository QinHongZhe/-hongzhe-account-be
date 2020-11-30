package com.p.v2iserver.account.controller;

import com.p.v2iserver.account.entitys.pojo.UserDTO;
import com.p.v2iserver.account.service.UserService;

import com.p.v2iserver.account.utils.CheckParamUtils;
import com.p.v2iserver.account.utils.NResult;
import io.swagger.annotations.Api;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: v2iserver
 * @description: 用户登录控制器
 * @author: QinHongZhe
 * @create: 2020-08-12 10:45
 **/
@RestController
@RequestMapping(value = {"/user", "/v1/account"})
@Api(value = "用户接口", tags = "UserController", description = "用户接口相关")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     *
     * TODO 用户管理
     * @author Pactera
     * @date 2020-11-16 14:34:07
     * @param: token
     * @param: userDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/setUser", method = RequestMethod.POST)
    @ResponseBody
    public NResult setUser(@RequestHeader("token") String token, @RequestBody @Valid UserDTO userDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = CheckParamUtils.checkDTO(userDTO, results);
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = userService.setUser(userDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 获取用户List
     * @author Pactera
     * @date 2020-11-16 14:34:18
     * @param: userDTO
     * @param: token
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public NResult getUserList(@RequestBody @Valid UserDTO userDTO,@RequestHeader("token") String token, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = userService.getUserList(userDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 批量删除用户
     * @author Pactera
     * @date 2020-11-16 14:34:51
     * @param: token
     * @param: userIds
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/batchDelUser", method = RequestMethod.POST)
    @ResponseBody
    public NResult batchDelUser(@RequestHeader("token") String token,@RequestBody Integer[] userIds){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = userService.batchDelUser(userIds,token);
        return nResult;
    }
}
