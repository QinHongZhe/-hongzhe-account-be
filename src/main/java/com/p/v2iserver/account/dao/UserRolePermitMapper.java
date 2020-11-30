package com.p.v2iserver.account.dao;

import com.p.v2iserver.account.entitys.pojo.UserRolePermitDTO;
import com.p.v2iserver.account.entitys.pojo.UserRolePermitVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserRolePermitMapper {
    List<UserRolePermitVO> getUserRolePermitList(UserRolePermitDTO userRoleDTO);
}
