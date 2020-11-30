package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-08 09:41
 **/
@Data
public class BaseVO {
    public String token;
    public Date createTime;
    public Date updateTime;
}
