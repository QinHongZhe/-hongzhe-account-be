package com.p.v2iserver.account.service;

import com.p.v2iserver.account.utils.NResult;
import com.p.v2iserver.account.entitys.pojo.RolePermitDTO;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-08 11:14
 **/
public interface RolePermitService {
    NResult setRolePermit(RolePermitDTO rolePermitDTO, String token);

    NResult getRolePermitList(RolePermitDTO rolePermitDTO, String token);

    NResult batchDelRolePermit(Integer[] rolePermitIds, String token);

    NResult setRolePermits(RolePermitDTO rolePermitDTO, String token);
}
