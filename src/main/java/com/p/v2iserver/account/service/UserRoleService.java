package com.p.v2iserver.account.service;

import com.p.v2iserver.account.utils.NResult;
import com.p.v2iserver.account.entitys.pojo.UserRoleDTO;

/**
 *
 * TODO 用户角色服务
 * @author Pactera
 * @date 2020-11-16 14:48:26
 **/
public interface UserRoleService {
    NResult setUserRole(UserRoleDTO userRoleDTO, String token);

    NResult getUserRoleList(UserRoleDTO userRoleDTO, String token);

    NResult batchDelUserRole(Integer[] userRoleIds, String token);
}
