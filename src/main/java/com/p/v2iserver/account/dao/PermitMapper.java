package com.p.v2iserver.account.dao;

import com.p.v2iserver.account.entitys.pojo.PermitDTO;
import com.p.v2iserver.account.entitys.pojo.PermitVO;
import com.p.v2iserver.account.entitys.pojo.RolePermitDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PermitMapper {
    List<PermitVO> getPermitList(PermitDTO permitDTO);

    List<PermitVO> getUserPermitList(RolePermitDTO permitDTO);

    List<PermitVO> getPermitListByPId(PermitDTO permitDTO);

    int updatePermit(PermitDTO permitDTO);

    int setPermit(PermitDTO permitDTO);

    int batchDelPermit(Integer[] permitIds);

    int delPermit(PermitDTO permitDTO);
}
