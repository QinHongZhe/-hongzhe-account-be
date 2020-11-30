package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 15:19
 **/
@Data
public class RoleDTO extends BaseDTO{
    public Integer id;
    @NotNull(message = "pid拼写错误")
    public Integer pid;
    @NotNull(message = "clientType拼写错误")
    public Integer clientType;
    @NotNull(message = "roleName拼写错误")
    @NotEmpty(message = "roleName不能为空，请您先填写roleName")
    public String roleName;
    @NotNull(message = "describes拼写错误")
    @NotEmpty(message = "describes不能为空，请您先填写describes")
    public String describes;
}
