package com.p.v2iserver.account.service;

import com.p.v2iserver.account.utils.NResult;
import com.p.v2iserver.account.entitys.pojo.UserRolePermitDTO;

/**
 *
 * TODO 用户角色权限服务
 * @author Pactera
 * @date 2020-11-16 14:47:51
 **/
public interface UserRolePermitService {
    NResult getUserRolePermitList(UserRolePermitDTO userRoleDTO);
}
