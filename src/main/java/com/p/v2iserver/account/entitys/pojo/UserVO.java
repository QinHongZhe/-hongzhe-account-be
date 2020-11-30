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
 * @create: 2020-09-07 15:19
 **/
@Data
public class UserVO extends BaseDTO{
    public Integer id;
    @NotNull(message = "username拼写错误")
    public String username;
    @NotNull(message = "password拼写错误")
    public String password;
}
