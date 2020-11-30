package com.p.v2iserver.account.dao;

import com.p.v2iserver.account.entitys.pojo.RoleDTO;
import com.p.v2iserver.account.entitys.pojo.RoleVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 16:28
 **/
@Mapper
public interface RoleMapper {
    int setRole(RoleDTO roleDTO);

    int updateRole(RoleDTO roleDTO);

    List<RoleVO> getRoleList(RoleDTO roleDTO);

    List<RoleVO> getRole(Integer pid);

    List<RoleVO> getRoleForDown(Integer pid);

    int batchDelRole(Integer[] roleIds);

    int delRole(RoleDTO roleDTO);
}
