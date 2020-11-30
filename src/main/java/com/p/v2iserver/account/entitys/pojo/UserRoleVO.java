package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: v2iserver
 * @description: UserRole
 * @author: QinHongZhe
 * @create: 2020-08-12 13:31
 **/
@Data
public class UserRoleVO extends BaseVO{
    public Integer userId;
    public String userName;
    public Integer roleId;
    public Integer rolePid;
    public Integer clientType;
    public String roleName;
    public String roleDesc;
    String createDateTime;
    String updateDateTime;
    List<UserRoleVO> children;
}
