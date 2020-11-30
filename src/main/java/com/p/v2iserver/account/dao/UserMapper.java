package com.p.v2iserver.account.dao;

import com.p.v2iserver.account.entitys.pojo.UserDTO;
import com.p.v2iserver.account.entitys.pojo.UserVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @program: v2iserver
 * @description: UserMapper
 * @author: QinHongZhe
 * @create: 2020-08-12 13:21
 **/
@Mapper
public interface UserMapper {
    List<UserVO> getUserList(UserDTO user);

    int setUser(UserDTO userDTO);

    int updateUser(UserDTO userDTO);

    int batchDelUser(Integer[] userIds);

    int delUser(UserDTO userDTO);
}
