package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 15:21
 **/
@Data
public class PermitDTO extends BaseDTO{
    public Integer id;
    public String name;
    public Integer pid;
    public Integer isNode;
    public String describes;
    public String  route;
}
