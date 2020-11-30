package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 15:22
 **/
@Data
public class RolePermitVO extends BaseVO{
    public Integer id;
    public Integer roleId;
    public Integer rolePid;
    public Integer clientType;
    public String roleName;
    public String roleDesc;
    public Integer permitId;
    public Integer[] permitIds;
    public String permitName;
    public Integer permitPid;
    public Integer isNode;
    public String permitDesc;
    public String route;
}
