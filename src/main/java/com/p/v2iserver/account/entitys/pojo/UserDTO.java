package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-08-14 10:48
 **/
@Data
public class UserDTO extends BaseDTO {
    public Integer id;
    @NotNull(message = "用户名拼写错误")
    public String username;
    @NotNull(message = "密码拼写错误")
    public String password = "654321";
}
