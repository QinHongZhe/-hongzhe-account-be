package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 15:25
 **/
@Data
public class UserRolePermitVO extends BaseVO{
    public Integer userId;
    public String username;
    public Integer roleId;
    public String rolePid;
    public Integer clientType;
    public String roleName;
    public String roleDesc;
    public Integer permitId;
    public String permitName;
    public Integer permitPid;
    public Integer isNode;
    public String permitDesc;
    public String route;
}
