package com.p.v2iserver.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p.v2iserver.account.dao.UserRoleMapper;
import com.p.v2iserver.account.entitys.pojo.RoleVO;
import com.p.v2iserver.account.entitys.pojo.UserDTO;
import com.p.v2iserver.account.entitys.pojo.UserRoleDTO;
import com.p.v2iserver.account.entitys.pojo.UserRoleVO;
import com.p.v2iserver.account.service.UserRoleService;
import com.p.v2iserver.account.service.UserService;
import com.p.v2iserver.account.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 17:01
 **/
@Service
@Log4j2
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserService userService;

    /**
     *
     * TODO 用户角色管理
     * @author Pactera
     * @date 2020-11-16 14:51:10
     * @param: userRoleDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult setUserRole(UserRoleDTO userRoleDTO, String token) {
        String message ;
        String username;
        String type = null;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"setUserRole");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = 0;
        if(MagicNumberConstant.ZERO==userRoleDTO.getType()){
            UserDTO userDTO = new UserDTO();
            userDTO.setType(userRoleDTO.getType());
            userDTO.setLimit(userRoleDTO.getLimit());
            userDTO.setPage(userRoleDTO.getPage());
            userDTO.setUsername(userRoleDTO.getUsername());
            userDTO.setPassword(DigestUtils.md5Hex(userRoleDTO.getPassword()));
            userDTO.setCreateTime(new Date());
            userDTO.setUpdateTime(userDTO.getCreateTime());
            NResult result = userService.setUser(userDTO, token);
            if(Objects.nonNull(result.getData())){
                UserDTO resultUser = (UserDTO)result.getData();
                userRoleDTO.setUserId(resultUser.getId());
            }
            code  = userRoleMapper.setUserRole(userRoleDTO);
            type = "添加";
        }else if(MagicNumberConstant.ONE==userRoleDTO.getType()){
            UserDTO userDTO = new UserDTO();
            userDTO.setType(userRoleDTO.getType());
            userDTO.setLimit(userRoleDTO.getLimit());
            userDTO.setPage(userRoleDTO.getPage());
            userDTO.setId(userRoleDTO.getUserId());
            userDTO.setUsername(userRoleDTO.getUsername());
            userDTO.setCreateTime(new Date());
            userService.setUser(userDTO, token);
            code  = userRoleMapper.updateUserRole(userRoleDTO);
            type = "修改";
        }else if(MagicNumberConstant.TWO==userRoleDTO.getType()){
            code  = userRoleMapper.delUserRole(userRoleDTO);
            type = "删除";
        }
        if(code < MagicNumberConstant.ONE) {
            message = "当前用户:【" + username + "】,该方法【" + "setUserRole" + "】"+type+"调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }else{
            message = "当前用户:【" + username + "】,该方法【" + "setUserRole" + "】"+type+"调用成功！！！";
            log.debug(message);
            return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
        }
    }

    /**
     *
     * TODO 获取用户角色List
     * @author Pactera
     * @date 2020-11-16 14:51:42
     * @param: userRoleDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult getUserRoleList(UserRoleDTO userRoleDTO, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"getUserRoleList");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(userRoleDTO.getPage(), userRoleDTO.getLimit());
//        List<UserRoleVO> userRoleVOList = userRoleMapper.getUserRoleList(userRoleDTO);
        List<UserRoleVO> parentList = userRoleMapper.getUserRole(userRoleDTO);
        List<UserRoleVO> userRoleVOList = userRoleMapper.getUserRoleForDown(userRoleDTO);
        List<UserRoleVO> resultList = this.getRoleList(parentList, userRoleVOList).stream().distinct().collect(Collectors.toList());
        if (ArrayUtil.isEmpty(parentList)) {
            message = "当前用户:【" + username + "】,该方法【" + "getUserRoleList" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "getUserRoleList" + "】调用成功！！！";
        log.debug(message);
        PageInfo<UserRoleVO> pageInfo = new PageInfo<>(resultList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(resultList);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,pdr);
    }

    /**
     *
     * TODO 获取角色List
     * @author Pactera
     * @date 2020-11-16 14:51:57
     * @param: parentList
     * @param: roleVOList
     * @return java.util.List<com.p.v2iserver.account.entitys.pojo.UserRoleVO>
     **/
    private List<UserRoleVO> getRoleList(List<UserRoleVO> parentList, List<UserRoleVO> roleVOList){
        List<UserRoleVO> resultList = new ArrayList<>();
        if(!ArrayUtil.isEmpty(parentList)){
            resultList.add(parentList.get(0));
        }
        if(!ArrayUtil.isEmpty(roleVOList)){
            for (UserRoleVO roleVO: roleVOList){
                resultList.add(roleVO);
                if(!ArrayUtil.isEmpty(roleVO.getChildren())){
                    resultList.addAll(this.getRoleList(null, roleVO.getChildren()));
                }
            }
        }
        if (!ArrayUtil.isEmpty(resultList)) {
            for (UserRoleVO entity : resultList) {
                entity.setCreateDateTime(DateUtil.convertDate2Str(entity.getCreateTime()));
                entity.setUpdateDateTime(DateUtil.convertDate2Str(entity.getUpdateTime()));
                entity.setChildren(null);
            }
        }
        return resultList;
    }

    /**
     *
     * TODO 批量删除用户角色
     * @author Pactera
     * @date 2020-11-16 14:52:29
     * @param: userRoleIds
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult batchDelUserRole(Integer[] userRoleIds, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"batchDelUserRole");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = userRoleMapper.batchDelUserRole(userRoleIds);
        if(code!=userRoleIds.length){
            message = "当前用户:【" + username + "】,该方法【" + "batchDelUserRole" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "batchDelUserRole" + "】调用成功！！！";
        log.debug(message);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
    }
}
