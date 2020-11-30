package com.p.v2iserver.account.utils;

import lombok.Data;

/**
 * @program: spring-cloud-study
 * @description:
 * @author: QinHongZhe
 * @create: 2020-07-24 09:48
 **/
@Data
public class NResult<T> {
    // 系统状态码
    private int code;
    // 系统消息
    private String message;
    // 数据
    private T data;

    private static NResult nResult=new NResult();
    public static NResult getInstance() {
        nResult.setCode(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getCode());
        nResult.setMessage(NStatusMessage.SystemStatus.SYS_FAIL_CODE.getMessage());
        nResult.setData(null);
        return nResult;
    }

    public NResult() {
    }

    public NResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
