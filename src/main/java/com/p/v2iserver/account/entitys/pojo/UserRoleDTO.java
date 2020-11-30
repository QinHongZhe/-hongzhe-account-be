package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 15:21
 **/
@Data
public class UserRoleDTO extends  BaseDTO{
    public Integer id;
    @NotNull(message = "userId拼写错误")
    public Integer userId;
    public String username;
    public String password;
    @NotNull(message = "roleId拼写错误")
    public Integer roleId;
    @NotNull(message = "rolePid拼写错误")
    public Integer rolePid;
    @NotNull(message = "clientType拼写错误")
    public Integer clientType;
    @NotNull(message = "roleName拼写错误")
    public String roleName;
    @NotNull(message = "roleDesc拼写错误")
    public String roleDesc;
}
