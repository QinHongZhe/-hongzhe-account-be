package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 15:22
 **/
@Data
public class PermitVO extends BaseVO{
    public Integer id;
    public String name;
    public Integer pid;
    public Integer isNode;
    public String describes;
    public String  route;
    private List<PermitVO> children = new ArrayList<>();
}
