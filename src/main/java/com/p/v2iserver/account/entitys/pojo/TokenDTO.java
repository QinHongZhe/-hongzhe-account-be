package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-10 13:22
 **/
@Data
public class TokenDTO extends BaseDTO {
    @NotNull(message = "username拼写错误")
    @NotEmpty(message = "username不能为空")
    public String username;
    @NotNull(message = "clientType拼写错误")
    public Integer clientType;
    @NotNull(message = "isRemeberMe拼写错误")
    public boolean isRemeberMe;

}
