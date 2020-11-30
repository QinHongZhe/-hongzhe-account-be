package com.p.v2iserver.account.service;

import com.p.v2iserver.account.utils.NResult;
import com.p.v2iserver.account.entitys.pojo.RoleDTO;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 16:29
 **/
public interface RoleService {

    NResult setRole(RoleDTO roleDTO, String token);

    NResult getRoleList(RoleDTO roleDTO, String token);

    NResult batchDelRole(Integer[] roleIds, String token);
}
