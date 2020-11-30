package com.p.v2iserver.account.utils;

import com.p.v2iserver.account.entitys.pojo.BaseVO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @program: spring-cloud-study
 * @description:
 * @author: QinHongZhe
 * @create: 2020-07-24 09:51
 **/
public class NResultUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    public static NResult error(String message){
        return new NResult(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,null);
    }

    public static <T> NResult error(String message, T data){
        if(null==data){
            data = (T) new BaseVO();
        }
        return new NResult(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode(),message,data);
    }

    public static <T>NResult error(int code,String message,T data){
        if(null==data){
            data = (T) new BaseVO();
        }
        return new NResult(code,message,data);
    }

    public static NResult success(String message){
        return new NResult(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,null);
    }

    public static <T>NResult success(String message,T data){
        if(null==data){
            data = (T) new BaseVO();
        }
        return new NResult(NStatusMessage.SystemStatus.SYS_SUCCESS_CODE.getCode(),message,data);
    }

    public static <T>NResult success(int code,String message,T data){
        if(null==data){
            data = (T) new BaseVO();
        }
        return new NResult(code,message,data);
    }
}
