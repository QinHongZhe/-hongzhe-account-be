package com.p.v2iserver.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p.v2iserver.account.dao.RolePermitMapper;
import com.p.v2iserver.account.entitys.pojo.RoleDTO;
import com.p.v2iserver.account.entitys.pojo.RolePermitDTO;
import com.p.v2iserver.account.entitys.pojo.RolePermitVO;
import com.p.v2iserver.account.service.RolePermitService;
import com.p.v2iserver.account.service.RoleService;
import com.p.v2iserver.account.utils.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-08 11:14
 **/
@Service
@Log4j2
public class RolePermitServiceImpl implements RolePermitService {

    @Autowired
    RolePermitMapper rolePermitMapper;

    @Autowired
    RoleService roleService;

    /**
     *
     * TODO 角色权限管理
     * @author Pactera
     * @date 2020-11-16 14:55:47
     * @param: rolePermitDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult setRolePermit(RolePermitDTO rolePermitDTO, String token) {
        String message ;
        String username;
        String type = null;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"setRolePermit");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = 0;
        if(MagicNumberConstant.ZERO==rolePermitDTO.getType()){
            code  = rolePermitMapper.setRolePermit(rolePermitDTO);
            type = "添加";
        }else if(MagicNumberConstant.ONE==rolePermitDTO.getType()){
            code  = rolePermitMapper.updateRolePermit(rolePermitDTO);
            type = "修改";
        }else if(MagicNumberConstant.TWO==rolePermitDTO.getType()){
            code  = rolePermitMapper.delRolePermit(rolePermitDTO);
            type = "删除";
        }
        if(code < MagicNumberConstant.ONE) {
            message = "当前用户:【" + username + "】,该方法【" + "setRolePermit" + "】"+type+"调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }else{
            message = "当前用户:【" + username + "】,该方法【" + "setRolePermit" + "】"+type+"调用成功！！！";
            log.debug(message);
            return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
        }
    }

    /**
     *
     * TODO 获取角色权限List
     * @author Pactera
     * @date 2020-11-16 14:56:10
     * @param: rolePermitDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult getRolePermitList(RolePermitDTO rolePermitDTO, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"getRolePermitList");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(rolePermitDTO.getPage(), rolePermitDTO.getLimit());
        List<RolePermitVO> rolePermitVOList = rolePermitMapper.getRolePermitList(rolePermitDTO);
        if (rolePermitVOList.size() <= 0 || null == rolePermitVOList) {
            message = "当前用户:【" + username + "】,该方法【" + "getRolePermitList" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "getRolePermitList" + "】调用成功！！！";
        log.debug(message);
        PageInfo<RolePermitVO> pageInfo = new PageInfo<>(rolePermitVOList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(rolePermitVOList);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,pdr);
    }

    /**
     *
     * TODO 批量删除角色权限
     * @author Pactera
     * @date 2020-11-16 14:56:34
     * @param: rolePermitIds
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult batchDelRolePermit(Integer[] rolePermitIds, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"batchDelRolePermit");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = rolePermitMapper.batchDelRolePermit(rolePermitIds);
        if(code!=rolePermitIds.length){
            message = "当前用户:【" + username + "】,该方法【" + "batchDelRolePermit" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "batchDelRolePermit" + "】调用成功！！！";
        log.debug(message);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
    }

    /**
     *
     * TODO 角色权限批量管理
     * @author Pactera
     * @date 2020-11-16 14:56:52
     * @param: rolePermitDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult setRolePermits(RolePermitDTO rolePermitDTO, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"setRolePermits");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleName(rolePermitDTO.getRoleName());
        roleDTO.setDescribes(rolePermitDTO.getRoleDesc());
        roleDTO.setId(rolePermitDTO.getRoleId());
        roleDTO.setType(rolePermitDTO.getType());
        roleDTO.setPid(rolePermitDTO.getRolePid());
        roleDTO.setClientType(rolePermitDTO.getClientType());
        roleService.setRole(roleDTO, token);
        rolePermitDTO.setRoleId(roleDTO.getId());
        List<RolePermitVO> rolePermitVOList = rolePermitMapper.getRolePermitList(rolePermitDTO);
        if (rolePermitVOList.size() <= 0 || null == rolePermitVOList) {
            message = "当前用户:【" + username + "】,该方法【" + "setRolePermits" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        List<Integer> rolePermitIds = new ArrayList<>();
        for(int i=0;i<rolePermitVOList.size();i++){
            rolePermitIds.add(rolePermitVOList.get(i).getId());
        }
        batchDelRolePermit(rolePermitIds.toArray(new Integer[rolePermitIds.size()]),token);
        int code =0;
        int returnCode = 0;
        for(int i=0;i<rolePermitDTO.getPermitIds().length;i++){
            RolePermitDTO rolePermitDTO1 = new RolePermitDTO();
            rolePermitDTO1.setType(0);
            rolePermitDTO1.setRoleId(rolePermitDTO.getRoleId());
            rolePermitDTO1.setPermitId(rolePermitDTO.getPermitIds()[i]);
            NResult nResult = setRolePermit(rolePermitDTO1,token);
            returnCode = nResult.getCode();
        }
        if(returnCode==NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode()){
            code = MagicNumberConstant.ZERO;
        }
        if(code!=MagicNumberConstant.ZERO){
            message = "当前用户:【" + username + "】,该方法【" + "setRolePermits" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "setRolePermits" + "】调用成功！！！";
        log.debug(message);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
    }
}
