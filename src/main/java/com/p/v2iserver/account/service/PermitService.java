package com.p.v2iserver.account.service;

import com.p.v2iserver.account.utils.NResult;
import com.p.v2iserver.account.entitys.pojo.PermitDTO;
import com.p.v2iserver.account.entitys.pojo.RolePermitDTO;

/**
 *
 * TODO 角色管理服务
 * @author Pactera
 * @date 2020-11-16 14:38:25
 **/
public interface PermitService {
    NResult setPermit(PermitDTO permitDTO, String token);

    NResult getPermitList(PermitDTO permitDTO, String token);

    /**
     *
     * TODO 获取角色对应的菜单List
     * @author Pactera
     * @date 2020-11-11 18:08:47
     * @param: permitDTO
     * @param: token
     * @return com.pactera.v2i.server.utils.NResult
     **/
    NResult getUserPermitList(RolePermitDTO permitDTO, String token);

    NResult batchDelPermit(Integer[] permitIds, String token);

    /**
     *
     * TODO 获取权限下关联权限列表
     * @author Pactera
     * @date 2020-11-09 11:29:35
     * @param: permitDTO
     * @param: token
     * @return com.pactera.v2i.server.utils.NResult
     **/
    NResult getPermitListByPId(PermitDTO permitDTO, String token);
}
