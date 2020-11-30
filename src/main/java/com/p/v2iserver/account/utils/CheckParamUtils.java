package com.p.v2iserver.account.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;

/**
 * @program: v2iserver
 * @description: 校验参数
 * @author: QinHongZhe
 * @create: 2020-08-27 09:11
 **/
@Log4j2
public class CheckParamUtils {

    public static String checkDTO(Object objDTO, BindingResult results){
        String message = "";
        if(null == objDTO ){
            log.debug("当前用户:【{}】,参数异常","username");
            return  message;
        }
        if (results.hasErrors()) {
            message = "当前用户:【"+"username"+"】,校验参数异常:【"+results.getFieldError().getField()+"】,【"+results.getFieldError().getDefaultMessage()+"】";
            log.debug(message);
            return message;
        }
        return message;
    }
}
