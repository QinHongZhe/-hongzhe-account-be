package com.p.v2iserver.account.dao;

import com.p.v2iserver.account.entitys.pojo.RoleVO;
import com.p.v2iserver.account.entitys.pojo.UserRoleDTO;
import com.p.v2iserver.account.entitys.pojo.UserRoleVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    List<UserRoleVO> getUserRoleList(UserRoleDTO userRoleDTO);

    List<UserRoleVO> getUserRole(UserRoleDTO userRoleDTO);

    List<UserRoleVO> getUserRoleForDown(UserRoleDTO userRoleDTO);

    int setUserRole(UserRoleDTO userRoleDTO);

    int updateUserRole(UserRoleDTO userRoleDTO);

    int batchDelUserRole(Integer[] userRoleIds);

    int batchDelUserRoleByUserId(Integer[] userIds);

    int batchDelUserRoleByRoleId(Integer[] roleIds);

    int delUserRole(UserRoleDTO userRoleDTO);
}
