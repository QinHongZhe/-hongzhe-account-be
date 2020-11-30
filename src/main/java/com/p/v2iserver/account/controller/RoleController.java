package com.p.v2iserver.account.controller;

import com.p.v2iserver.account.entitys.pojo.RoleDTO;
import com.p.v2iserver.account.service.RoleService;
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
 * @create: 2020-09-07 16:24
 **/
@RestController
@RequestMapping(value = {"/role", "/v1/account"})
@Api(value = "角色接口", tags = "RoleController", description = "角色接口相关")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     *
     * TODO 角色管理
     * @author Pactera
     * @date 2020-11-16 14:28:58
     * @param: token
     * @param: roleDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/setRole", method = RequestMethod.POST)
    @ResponseBody
    public NResult setRole(@RequestHeader("token") String token,@RequestBody @Valid RoleDTO roleDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = CheckParamUtils.checkDTO(roleDTO, results);
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = roleService.setRole(roleDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 获取角色List
     * @author Pactera
     * @date 2020-11-16 14:29:23
     * @param: token
     * @param: roleDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    @ResponseBody
    public NResult getRoleList(@RequestHeader("token") String token,@RequestBody @Valid RoleDTO roleDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = roleService.getRoleList(roleDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 批量删除角色
     * @author Pactera
     * @date 2020-11-16 14:29:47
     * @param: token
     * @param: roleIds
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/batchDelRole", method = RequestMethod.POST)
    @ResponseBody
    public NResult batchDelRole(@RequestHeader("token") String token,@RequestBody Integer[] roleIds){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = roleService.batchDelRole(roleIds,token);
        return nResult;
    }

}
