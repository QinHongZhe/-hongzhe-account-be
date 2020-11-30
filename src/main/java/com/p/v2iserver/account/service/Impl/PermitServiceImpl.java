package com.p.v2iserver.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p.v2iserver.account.dao.PermitMapper;
import com.p.v2iserver.account.entitys.pojo.PermitDTO;
import com.p.v2iserver.account.entitys.pojo.PermitVO;
import com.p.v2iserver.account.entitys.pojo.RolePermitDTO;
import com.p.v2iserver.account.service.PermitService;
import com.p.v2iserver.account.service.RolePermitService;
import com.p.v2iserver.account.utils.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-08 09:45
 **/
@Service
@Log4j2
public class PermitServiceImpl implements PermitService {

    @Autowired
    PermitMapper permitMapper;

    @Autowired
    RolePermitService rolePermitService;

    /**
     *
     * TODO 权限管理
     * @author Pactera
     * @date 2020-11-16 14:54:18
     * @param: permitDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult setPermit(PermitDTO permitDTO, String token) {

        String message ;
        String username;
        String type = null;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"setPermit");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = 0;
        if(MagicNumberConstant.ZERO==permitDTO.getType()){
            code  = permitMapper.setPermit(permitDTO);
            type = "添加";
        }else if(MagicNumberConstant.ONE==permitDTO.getType()){
            code  = permitMapper.updatePermit(permitDTO);
            type = "修改";
        }else if(MagicNumberConstant.TWO==permitDTO.getType()){
            code  = permitMapper.delPermit(permitDTO);
            type = "删除";
        }
        if(code < MagicNumberConstant.ONE) {
            message = "当前用户:【" + username + "】,该方法【" + "setPermit" + "】"+type+"调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }else{
            message = "当前用户:【" + username + "】,该方法【" + "setPermit" + "】"+type+"调用成功！！！";
            log.debug(message);
            return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
        }
    }

    /**
     *
     * TODO 获取权限List
     * @author Pactera
     * @date 2020-11-16 14:54:36
     * @param: permitDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult getPermitList(PermitDTO permitDTO, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"getPermitList");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(permitDTO.getPage(), permitDTO.getLimit());
        List<PermitVO> permitVOList = permitMapper.getPermitList(permitDTO);
        List<PermitVO> permitVOListNew =  RolePermListToTree(permitVOList);
        if (permitVOListNew.size() <= 0 || null == permitVOListNew) {
            message = "当前用户:【" + username + "】,该方法【" + "getPermitList" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "getPermitList" + "】调用成功！！！";
        log.debug(message);
        PageInfo<PermitVO> pageInfo = new PageInfo<>(permitVOListNew);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(permitVOListNew);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,pdr);
    }

    /**
     *
     * TODO 获取角色对应的菜单List
     * @author Pactera
     * @date 2020-11-11 18:12:52
     * @param: permitDTO
     * @param: token
     * @return com.pactera.v2i.server.utils.NResult
     **/
    @Override
    public NResult getUserPermitList(RolePermitDTO permitDTO, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"getUserPermitList");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(permitDTO.getPage(), permitDTO.getLimit());
        List<PermitVO> permitVOList = permitMapper.getUserPermitList(permitDTO);
        List<PermitVO> permitVOListNew =  RolePermListToTree(permitVOList);
        if (permitVOListNew.size() <= 0 || null == permitVOListNew) {
            message = "当前用户:【" + username + "】,该方法【" + "getUserPermitList" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "getUserPermitList" + "】调用成功！！！";
        log.debug(message);
        PageInfo<PermitVO> pageInfo = new PageInfo<>(permitVOListNew);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(permitVOListNew);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,pdr);
    }


    /**
     *
     * TODO 角色权限List 转Tree
     * @author Pactera
     * @date 2020-11-16 14:54:59
     * @param: list
     * @return java.util.List<com.p.v2iserver.account.entitys.pojo.PermitVO>
     **/
    public static List<PermitVO> RolePermListToTree(List<PermitVO> list){

        Map<Integer, PermitVO> mapTmp = new HashMap<>();
        for (PermitVO current : list) {
            mapTmp.put(current.getId(), current);
        }
        List<PermitVO> finalList = new ArrayList<>();

        mapTmp.forEach((k, v) -> {
            if(v.getPid()==0) {
                finalList.add(v);
            } else {
                mapTmp.get(v.getPid()).getChildren().add(v);
            }
        });
        return finalList;
    }

    /**
     *
     * TODO 批量删除权限
     * @author Pactera
     * @date 2020-11-16 14:55:23
     * @param: permitIds
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult batchDelPermit(Integer[] permitIds, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"batchDelPermit");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = permitMapper.batchDelPermit(permitIds);
        if(code!=permitIds.length){
            message = "当前用户:【" + username + "】,该方法【" + "batchDelPermit" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "batchDelPermit" + "】调用成功！！！";
        log.debug(message);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
    }

    /**
     *
     * TODO 获取权限下关联权限列表
     * @author Pactera
     * @date 2020-11-09 11:29:35
     * @param: permitDTO
     * @param: token
     * @return com.pactera.v2i.server.utils.NResult
     **/
    @Override
    public NResult getPermitListByPId(PermitDTO permitDTO, String token) {
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"getPermitList");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(permitDTO.getPage(), permitDTO.getLimit());
        List<PermitVO> permitVOList = permitMapper.getPermitListByPId(permitDTO);
        PageInfo<PermitVO> pageInfo = new PageInfo<>(permitVOList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(permitVOList);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),String.valueOf(obj),pdr);
    }
}
