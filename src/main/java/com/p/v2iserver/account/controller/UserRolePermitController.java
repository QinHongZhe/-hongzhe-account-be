package com.p.v2iserver.account.controller;

import com.p.v2iserver.account.entitys.pojo.UserRoleDTO;
import com.p.v2iserver.account.entitys.pojo.UserRolePermitDTO;
import com.p.v2iserver.account.service.UserRolePermitService;
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
 * @create: 2020-09-08 14:27
 **/
@RestController
@RequestMapping(value = {"/userRolePermit", "/v1/account"})
@Api(value = "用户角色权限接口", tags = "UserRolePermitController", description = "用户角色权限接口相关")
public class UserRolePermitController {

    @Autowired
    UserRolePermitService userRolePermitService;

    /**
     *
     * TODO 获取用户角色关联菜单List
     * @author Pactera
     * @date 2020-11-16 14:36:04
     * @param: userRoleDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public NResult getUserRolePermitList(@RequestBody @Valid UserRolePermitDTO userRoleDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = userRolePermitService.getUserRolePermitList(userRoleDTO);
        return nResult;
    }
}
