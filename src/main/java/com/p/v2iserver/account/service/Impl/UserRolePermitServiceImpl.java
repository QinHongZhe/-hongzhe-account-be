package com.p.v2iserver.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.p.v2iserver.account.dao.UserRolePermitMapper;
import com.p.v2iserver.account.entitys.pojo.UserRolePermitDTO;
import com.p.v2iserver.account.entitys.pojo.UserRolePermitVO;
import com.p.v2iserver.account.entitys.pojo.UserRoleVO;
import com.p.v2iserver.account.service.UserRolePermitService;
import com.p.v2iserver.account.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-08 15:28
 **/
@Service
@Log4j2
public class UserRolePermitServiceImpl implements UserRolePermitService {

    @Autowired
    UserRolePermitMapper userRolePermitMapper;

    /**
     *
     * TODO 获取用户角色权限服务List
     * @author Pactera
     * @date 2020-11-16 14:50:37
     * @param: userRoleDTO
     * @return com.pactera.account.be.account.business.utils.NResult
     **/
    @Override
    public NResult getUserRolePermitList(UserRolePermitDTO userRoleDTO) {
        PageDataResult pdr = new PageDataResult();
        userRoleDTO.setPassword(DigestUtils.md5Hex(userRoleDTO.getPassword()));
        PageHelper.startPage(userRoleDTO.getPage(), userRoleDTO.getLimit());
        List<UserRolePermitVO> userRolePermitVOList = userRolePermitMapper.getUserRolePermitList(userRoleDTO);
        String message = "";
        if (userRolePermitVOList.size() <= 0 || null == userRolePermitVOList) {
            message = "当前用户:【" + userRoleDTO.getUsername() + "】,该方法【" + "getUserRolePermitList" + "】调用失败！！！";
            log.debug(message);
            return NResultUtil.error(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
        }
        // 根据用户名和客户端角色生成token
        String token =  JwtTokenUtils.createToken(userRolePermitVOList.get(0).getUsername(),userRolePermitVOList.get(0).getClientType().toString(),false);
        userRolePermitVOList.get(0).setToken(token);
        message = "当前用户:【" + userRoleDTO.getUsername() + "】,该方法【" + "getUserRolePermitList" + "】调用成功！！！";
        log.debug(message);
        PageInfo<UserRolePermitVO> pageInfo = new PageInfo<>(userRolePermitVOList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(userRolePermitVOList);
        return NResultUtil.success(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,pdr);
    }
}
