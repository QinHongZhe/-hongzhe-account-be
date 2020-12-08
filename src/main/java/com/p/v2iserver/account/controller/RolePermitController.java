package com.p.v2iserver.account.controller;

import com.p.v2iserver.account.entitys.pojo.RolePermitDTO;
import com.p.v2iserver.account.service.RolePermitService;
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
 * @create: 2020-09-08 11:11
 **/
@RestController
@RequestMapping(value = {"/rolePermit", "/v2/account"})
@Api(value = "角色权限接口", tags = "RolePermitController", description = "角色权限接口相关")
public class RolePermitController {

    @Autowired
    RolePermitService rolePermitService;


    /**
     *
     * TODO 角色关联权限管理
     * @author Pactera
     * @date 2020-11-16 14:31:34
     * @param: token
     * @param: rolePermitDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/setRolePermits", method = RequestMethod.POST)
    @ResponseBody
    public NResult setRolePermits(@RequestHeader("token") String token,@RequestBody @Valid RolePermitDTO rolePermitDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = CheckParamUtils.checkDTO(rolePermitDTO, results);
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = rolePermitService.setRolePermits(rolePermitDTO,token);
        return nResult;
    }


    /**
     *
     * TODO 角色关联权限管理
     * @author Pactera
     * @date 2020-11-16 14:32:18
     * @param: token
     * @param: rolePermitDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/setRolePermit", method = RequestMethod.POST)
    @ResponseBody
    public NResult setRolePermit(@RequestHeader("token") String token,@RequestBody @Valid RolePermitDTO rolePermitDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = CheckParamUtils.checkDTO(rolePermitDTO, results);
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = rolePermitService.setRolePermit(rolePermitDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 获取角色关联权限List
     * @author Pactera
     * @date 2020-11-16 14:32:31
     * @param: token
     * @param: rolePermitDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/getRolePermitList", method = RequestMethod.POST)
    @ResponseBody
    public NResult getRolePermitList(@RequestHeader("token") String token,@RequestBody @Valid RolePermitDTO rolePermitDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = CheckParamUtils.checkDTO(rolePermitDTO, results);
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = rolePermitService.getRolePermitList(rolePermitDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 批量删除角色关联权限
     * @author Pactera
     * @date 2020-11-16 14:32:57
     * @param: token
     * @param: rolePermitIds
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/batchDelRolePermit", method = RequestMethod.POST)
    @ResponseBody
    public NResult batchDelRolePermit(@RequestHeader("token") String token,@RequestBody Integer[] rolePermitIds){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = rolePermitService.batchDelRolePermit(rolePermitIds,token);
        return nResult;
    }
}
