package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 15:24
 **/
@Data
public class UserRolePermitDTO extends BaseDTO{
    public Integer userId;
    @NotNull(message = "username拼写错误")
    @NotEmpty(message = "username不能为空，请您先填写username")
    public String username;
    @NotNull(message = "password拼写错误")
    @NotEmpty(message = "password不能为空，请您先填写password")
    public String password;
    public Integer roleId;
    public Integer rolePid;
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
