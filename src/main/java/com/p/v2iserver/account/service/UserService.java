package com.p.v2iserver.account.service;


import com.p.v2iserver.account.utils.NResult;
import com.p.v2iserver.account.entitys.pojo.UserDTO;

/**
 * @program: v2iserver
 * @description: UserService
 * @author: QinHongZhe
 * @create: 2020-08-12 13:14
 **/
public interface UserService {
    NResult getUserList(UserDTO userDTO, String token);

    NResult setUser(UserDTO userDTO, String token);

    NResult batchDelUser(Integer[] userIds, String token);
}
