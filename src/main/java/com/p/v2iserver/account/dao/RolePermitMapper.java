package com.p.v2iserver.account.dao;

import com.p.v2iserver.account.entitys.pojo.RolePermitDTO;
import com.p.v2iserver.account.entitys.pojo.RolePermitVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RolePermitMapper {

    List<RolePermitVO> getRolePermitList(RolePermitDTO rolePermitDTO);

    int updateRolePermit(RolePermitDTO rolePermitDTO);

    int setRolePermit(RolePermitDTO rolePermitDTO);

    int batchDelRolePermit(Integer[] rolePermitIds);

    int delRolePermit(RolePermitDTO rolePermitDTO);

    int setRolePermits(RolePermitDTO rolePermitDTO);
}
