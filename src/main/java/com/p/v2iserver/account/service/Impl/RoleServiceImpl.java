package com.p.v2iserver.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p.v2iserver.account.dao.RoleMapper;
import com.p.v2iserver.account.entitys.pojo.RoleDTO;
import com.p.v2iserver.account.entitys.pojo.RoleVO;
import com.p.v2iserver.account.service.RoleService;
import com.p.v2iserver.account.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 16:29
 **/
@Service
@Log4j2
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    /**
     *
     * TODO 角色管理
     * @author Pactera
     * @date 2020-11-16 14:57:55
     * @param: roleDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult setRole(RoleDTO roleDTO, String token) {

        String message ;
        String username;
        String type = null;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"setRole");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = 0;
        if(MagicNumberConstant.ZERO==roleDTO.getType()){
            code  = roleMapper.setRole(roleDTO);
            type = "添加";
        }else if(MagicNumberConstant.ONE==roleDTO.getType()){
            code  = roleMapper.updateRole(roleDTO);
            type = "修改";
        }else if(MagicNumberConstant.TWO==roleDTO.getType()){
            code  = roleMapper.delRole(roleDTO);
            type = "删除";
        }
        if(code < MagicNumberConstant.ONE) {
            message = "当前用户:【" + username + "】,该方法【" + "setRole" + "】"+type+"调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }else{
            message = "当前用户:【" + username + "】,该方法【" + "setRole" + "】"+type+"调用成功！！！";
            log.debug(message);
            return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
        }
    }

    /**
     *
     * TODO 获取角色List
     * @author Pactera
     * @date 2020-11-16 14:58:10
     * @param: roleDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult getRoleList(RoleDTO roleDTO, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"getRoleList");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(roleDTO.getPage(), roleDTO.getLimit());
//        List<RoleVO> roleVOList = roleMapper.getRoleList(roleDTO);
        List<RoleVO> roleVOList = roleMapper.getRoleForDown(roleDTO.pid);
        List<RoleVO> parentRoleList = roleMapper.getRole(roleDTO.pid);
        List<RoleVO> resultList = this.getRoleList(parentRoleList, roleVOList);
        if (ArrayUtil.isEmpty(parentRoleList)) {
            message = "当前用户:【" + username + "】,该方法【" + "getRoleList" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "getRoleList" + "】调用成功！！！";
        log.debug(message);
        PageInfo<RoleVO> pageInfo = new PageInfo<>(resultList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(resultList);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,pdr);
    }

    /**
     *
     * TODO tree convert list
     * @author Pactera
     * @date 2020-11-13 10:37:00
     * @param: roleVOList
     * @return java.util.List<com.pactera.v2i.server.entitys.pojo.RoleVO>
     **/
    private List<RoleVO> getRoleList(List<RoleVO> parentList, List<RoleVO> roleVOList){
        List<RoleVO> resultList = new ArrayList<>();
        if(!ArrayUtil.isEmpty(parentList)){
            resultList.add(parentList.get(0));
        }
        if(!ArrayUtil.isEmpty(roleVOList)){
            for (RoleVO roleVO: roleVOList){
                resultList.add(roleVO);
                if(!ArrayUtil.isEmpty(roleVO.getChildren())){
                    resultList.addAll(this.getRoleList(null, roleVO.getChildren()));
                }
            }
        }
        if (!ArrayUtil.isEmpty(resultList)) {
            for (RoleVO entity : resultList) {
                entity.setCreateDateTime(DateUtil.convertDate2Str(entity.getCreateTime()));
                entity.setUpdateDateTime(DateUtil.convertDate2Str(entity.getUpdateTime()));
                entity.setChildren(null);
            }
        }
        return resultList;
    }

    /**
     *
     * TODO 批量删除角色
     * @author Pactera
     * @date 2020-11-16 14:58:28
     * @param: roleIds
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult batchDelRole(Integer[] roleIds, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"batchDelRole");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = roleMapper.batchDelRole(roleIds);
        if(code!=roleIds.length){
            message = "当前用户:【" + username + "】,该方法【" + "batchDelRole" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "batchDelRole" + "】调用成功！！！";
        log.debug(message);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
    }
}
