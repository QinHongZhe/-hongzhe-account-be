package com.p.v2iserver.account.utils;

import java.util.List;


/**
 * 数组Util
 *
 * @author Pactera
 * @date 2020/11/03
 */
public class ArrayUtil {

    /**
     * 是空的
     * 是否为null或 size==0
     *
     * @param list 列表
     * @return boolean
     */
    public static boolean isEmpty(List list) {
        return null==list || list.size()<=0;
    }
}
