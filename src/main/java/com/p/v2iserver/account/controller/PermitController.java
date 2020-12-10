package com.p.v2iserver.account.controller;

import com.p.v2iserver.account.entitys.pojo.PermitDTO;
import com.p.v2iserver.account.entitys.pojo.RolePermitDTO;
import com.p.v2iserver.account.service.PermitService;
import com.p.v2iserver.account.utils.CheckParamUtils;
import com.p.v2iserver.account.utils.NResult;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-08 09:21
 **/
@RestController
@RequestMapping(value = {"/permit", "/v1/account"})
@Api(value = "权限接口", tags = "PermitController", description = "权限接口相关")
@Log4j2
public class PermitController {

    @Autowired
    PermitService permitService;

    /**
     *
     * TODO 权限管理
     * @author Pactera
     * @date 2020-11-16 14:21:12
     * @param: token
     * @param: permitDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/setPermit", method = RequestMethod.POST)
    @ResponseBody
    public NResult setPermit(@RequestHeader("token") String token,@RequestBody @Valid PermitDTO permitDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = CheckParamUtils.checkDTO(permitDTO, results);
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = permitService.setPermit(permitDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 获取权限列表
     * @author Pactera
     * @date 2020-11-16 14:21:08
     * @param: token
     * @param: permitDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/getPermitList", method = RequestMethod.POST)
    @ResponseBody
    public NResult getPermitList(@RequestHeader("token") String token,@RequestBody @Valid PermitDTO permitDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = permitService.getPermitList(permitDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 获取角色对应的菜单List
     * @author Pactera
     * @date 2020-11-16 14:27:36
     * @param: token
     * @param: permitDTO
     * @param: results
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/getUserPermitList", method = RequestMethod.POST)
    @ResponseBody
    public NResult getUserPermitList(@RequestHeader("token") String token, @RequestBody @Valid RolePermitDTO permitDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = permitService.getUserPermitList(permitDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 获取权限下关联权限列表
     * @author Pactera
     * @date 2020-11-09 11:30:17
     * @param: token
     * @param: permitDTO
     * @param: results
     * @return com.pactera.v2i.server.utils.NResult
     **/
    @RequestMapping(value = "/getPermitListByPId", method = RequestMethod.POST)
    @ResponseBody
    public NResult getPermitListByPId(@RequestHeader("token") String token,@RequestBody @Valid PermitDTO permitDTO, BindingResult results){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
//        nResult = permitService.getPermitListByPId(permitDTO,token);
        return nResult;
    }

    /**
     *
     * TODO 批量删除权限
     * @author Pactera
     * @date 2020-11-16 14:27:45
     * @param: token
     * @param: permitIds
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @RequestMapping(value = "/batchDelPermit", method = RequestMethod.POST)
    @ResponseBody
    public NResult batchDelPermit(@RequestHeader("token") String token,@RequestBody Integer[] permitIds){
        NResult nResult =  NResult.getInstance();
        String message = "";
        if(""!=message){
            nResult.setMessage(message);
            return nResult;
        }
        nResult = permitService.batchDelPermit(permitIds,token);
        return nResult;
    }

}
