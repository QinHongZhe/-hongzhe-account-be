package com.p.v2iserver.account.entitys.pojo;

import lombok.Data;

import java.util.*;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-07 15:20
 **/
@Data
public class RoleVO extends BaseVO{
    public Integer id;
    public Integer pid;
    public Integer clientType;
    public String roleName;
    public String describes;
    String createDateTime;
    String updateDateTime;
    List<RoleVO> children;
}
