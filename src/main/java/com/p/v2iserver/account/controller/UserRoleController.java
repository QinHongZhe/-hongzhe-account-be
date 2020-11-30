package com.p.v2iserver.account.controller;

import com.p.v2iserver.account.entitys.pojo.UserRoleDTO;
import com.p.v2iserver.account.service.UserRoleService;
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
 * @create: 2020-09-07 16:58
 **/
@RestController
@RequestMapping(value = {"/userRole", "/v1/account"})
@Api(value = "用户角色绑定接口", tags = "UserRoleController", description = "用户角色绑定接口相关")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    /**
     *
     * TODO 用户关联角色管理
     * @author Pactera
     * @date 2020-11-16 14:35:08
     * @param: token
     * @param: userRoleDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/setUserRole", method = RequestMethod.POST)
    @ResponseBody
    public NResult setUserRole(@RequestHeader("token") String token,@RequestBody @Valid UserRoleDTO userRoleDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = CheckParamUtils.checkDTO(userRoleDTO, results);
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = userRoleService.setUserRole(userRoleDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 获取用户关联角色List
     * @author Pactera
     * @date 2020-11-16 14:35:29
     * @param: token
     * @param: userRoleDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/getUserRoleList", method = RequestMethod.POST)
    @ResponseBody
    public NResult getUserRoleList(@RequestHeader("token") String token,@RequestBody @Valid UserRoleDTO userRoleDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = userRoleService.getUserRoleList(userRoleDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 批量删除用户
     * @author Pactera
     * @date 2020-11-16 14:35:50
     * @param: token
     * @param: userRoleIds
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/batchDelUserRole", method = RequestMethod.POST)
    @ResponseBody
    public NResult batchDelUserRole(@RequestHeader("token") String token,@RequestBody Integer[] userRoleIds){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = userRoleService.batchDelUserRole(userRoleIds,token);
        return nResult;
    }
}
