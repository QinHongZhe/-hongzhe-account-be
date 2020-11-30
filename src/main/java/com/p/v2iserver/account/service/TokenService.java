package com.p.v2iserver.account.service;

import com.p.v2iserver.account.utils.NResult;
import com.p.v2iserver.account.entitys.pojo.TokenDTO;

/**
 * @program: v2iserver
 * @description:
 * @author: QinHongZhe
 * @create: 2020-09-10 13:26
 **/
public interface TokenService {
    NResult setToken(TokenDTO tokenDTO);
}
