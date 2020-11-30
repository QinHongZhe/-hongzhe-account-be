package com.p.v2iserver.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p.v2iserver.account.dao.UserMapper;
import com.p.v2iserver.account.entitys.pojo.UserDTO;
import com.p.v2iserver.account.entitys.pojo.UserVO;
import com.p.v2iserver.account.service.UserService;
import com.p.v2iserver.account.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: v2iserver
 * @description: UserServiceImpl
 * @author: QinHongZhe
 * @create: 2020-08-12 13:20
 **/
@Service
@Log4j2

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisUtils redisUtils;

    /**
     *
     * TODO 获取用户List
     * @author Pactera
     * @date 2020-11-16 14:53:12
     * @param: userDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult getUserList(UserDTO userDTO, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"getUserList");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        PageDataResult pdr = new PageDataResult();
        PageHelper.startPage(userDTO.getPage(), userDTO.getLimit());
        List<UserVO> userVOList = userMapper.getUserList(userDTO);
        if (userVOList.size() <= 0 || null == userVOList) {
            message = "当前用户:【" + username+ "】,该方法【" + "getUserList" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "getUserList" + "】调用成功！！！";
        log.debug(message);
        PageInfo<UserVO> pageInfo = new PageInfo<>(userVOList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(userVOList);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,pdr);
    }

    /**
     *
     * TODO 用户管理
     * @author Pactera
     * @date 2020-11-16 14:53:30
     * @param: userDTO
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult setUser(UserDTO userDTO, String token) {

        String message ;
        String username;
        String type = null;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"setUser");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = 0;
        userDTO.setPassword(DigestUtils.md5Hex(userDTO.getPassword()));
        if(MagicNumberConstant.ZERO==userDTO.getType()){
            code  = userMapper.setUser(userDTO);
            type = "添加";
        }else if(MagicNumberConstant.ONE==userDTO.getType()){
            code  = userMapper.updateUser(userDTO);
            type = "修改";
        }else if(MagicNumberConstant.TWO==userDTO.getType()){
            code  = userMapper.delUser(userDTO);
            type = "删除";
        }
        if(code < MagicNumberConstant.ONE) {
            message = "当前用户:【" + username + "】,该方法【" + "setUser" + "】"+type+"调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }else{
            message = "当前用户:【" + username + "】,该方法【" + "setUser" + "】"+type+"调用成功！！！";
            log.debug(message);
            return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,userDTO);
        }
    }

    /**
     *
     * TODO 批量删除用户
     * @author Pactera
     * @date 2020-11-16 14:53:54
     * @param: userIds
     * @param: token
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult batchDelUser(Integer[] userIds, String token) {
        String message;
        String username ;
        Object obj =  JwtTokenUtils.tokenTimeOut(token,"batchDelUser");
        if(obj instanceof NResult){
            return (NResult) obj;
        }
        username = (String) obj;
        int code = userMapper.batchDelUser(userIds);
        if(code!=userIds.length){
            message = "当前用户:【" + username + "】,该方法【" + "batchDelUser" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        message = "当前用户:【" + username + "】,该方法【" + "batchDelUser" + "】调用成功！！！";
        log.debug(message);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
    }
}
