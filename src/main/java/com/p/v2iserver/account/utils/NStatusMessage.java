package com.p.v2iserver.account.utils;

public interface NStatusMessage {
    enum SystemStatus implements NStatusMessage {
        SYS_SUCCESS_CODE(200,"SUCCESS"),
        SYS_FAIL_CODE(400,"FAIL"),
        SYS_BAD_REQUEST_CODE(403,"请求有误"),
        SYS_NOT_FOUND_CODE(404,"您访问的资源不存在"),
        SYS_SERVER_EPT_CODE(500,"操作异常，请稍后再试"),
        SYS_REQUES_PARAM_EXCEPTION_CODE(401,"参数异常"),
        TOKEN_FAIL_CODE(600,"token失效"),
        SESSION_FAIL_CODE(602,"Session失效"),
        TOKEN_FLUSH_SUCCESS_CODE(601,"token刷新成功"),
        WEBSOCKET_EXCPTION_CODE(700,"webSocket异常或者错误"),

        LOGIN_SUCCESS(1002,"LOGIN_SUCCESS"),
        LOGIN_FAIL(1003,"LOGIN_FAIL"),
        REGIST_SUCCESS(1004,"LOGIN_SUCCESS"),
        REGITS_FAIL(1005,"LOGIN_FAIL"),


        /**
         *  格式定义：
         *  0-1999 系统保留
         *  其他业务：状态码由 4 位数字组成
         *  第一位：对应 permission 表中的 id 若菜单增加 对应增加状态码位数
         *  第二位：目前默认是 0  可以后面拓展操作
         *  第三位：0 成功  1 失败
         *  第四位：0 增加 1 删除 2 修改 3 查询  4 请求参数异常 5 超时 6 错误 9 自定义或者其他
         */

        USER_CREATE_SUCCESS_CODE(2000,"用户添加成功"),
        USER_DEL_SUCCESS_CODE(2001,"用户删除成功"),
        USER_UPDATEA_SUCCESS_CODE(2002,"用户更新成功"),
        USER_GET_SUCCESS_CODE(2003,"用户查询成功"),
        USER_REQUEST_PARAM_EXCEPTION_CODE(2004,"请求参数异常"),
        USER_TIMEOUT_CODE(2005,"请求超时"),
        USER_UNKNOW_CODE(2006,"未知错误"),
        USER_LOCK_CODE(2007,"用户已经锁定"),
        USER_LOCK_FIVE_CODE(2007,"用户名或密码错误次数大于5次,账户已锁定!2分钟后可再次登录，或联系管理员解锁"),
        USER_GET_USERISROLES_SUCCESS_CODE(2008,"获取用户角色成功"),

        USER_CREATE_FAIL_CODE(2010,"用户添加失败"),
        USER_DEL_FAIL_CODE(2011,"用户删除失败"),
        USER_UPDATEA_FAIL_CODE(2012,"用户更新失败"),
        USER_GET_FAIL_CODE(2013,"用户查询失败"),
        USER_GET_USERISROLES_FAIL_CODE(2008,"获取用户角色失败"),


        ROLE_CREATE_SUCCESS_CODE(3000,"角色添加成功"),
        ROLE_DEL_SUCCESS_CODE(3001,"角色删除成功"),
        ROLE_UPDATEA_SUCCESS_CODE(3002,"角色更新成功"),
        ROLE_GET_SUCCESS_CODE(3003,"角色查询成功"),
        ROLE_REQUEST_PARAM_EXCEPTION_CODE(3004,"请求参数异常"),
        ROLE_TIMEOUT_CODE(3005,"请求超时"),
        ROLE_UNKNOW_CODE(3006,"未知错误"),


        ROLE_CREATE_FAIL_CODE(3010,"角色添加失败"),
        ROLE_DEL_FAIL_CODE(3011,"角色删除失败"),
        ROLE_UPDATEA_FAIL_CODE(3012,"角色更新失败"),
        ROLE_GET_FAIL_CODE(3013,"角色查询失败"),

        PERMIS_CREATE_SUCCESS_CODE(4000,"权限添加成功"),
        PERMIS_DEL_SUCCESS_CODE(4001,"权限删除成功"),
        PERMIS_UPDATEA_SUCCESS_CODE(4002,"权限更新成功"),
        PERMIS_GET_SUCCESS_CODE(4003,"权限查询成功"),
        PERMIS_REQUEST_PARAM_EXCEPTION_CODE(4004,"请求参数异常"),
        PERMIS_TIMEOUT_CODE(4005,"请求超时"),
        PERMIS_UNKNOW_CODE(4006,"未知错误"),

        PERMIS_CREATE_FAIL_CODE(4010,"权限添加失败"),
        PERMIS_DEL_FAIL_CODE(4011,"权限删除失败"),
        PERMIS_UPDATEA_FAIL_CODE(4012,"权限更新失败"),
        PERMIS_GET_FAIL_CODE(4013,"权限查询失败"),

        TRAFFIClIGHT_CREATE_SUCCESS_CODE(6000,"红绿灯配置添加成功"),
        TRAFFIClIGHT_DEL_SUCCESS_CODE(6001,"红绿灯配置删除成功"),
        TRAFFIClIGHT_UPDATEA_SUCCESS_CODE(6002,"红绿灯配置更新成功"),
        TRAFFIClIGHT_GET_SUCCESS_CODE(6003,"红绿灯配置查询成功"),
        TRAFFIClIGHT_REQUEST_PARAM_EXCEPTION_CODE(4004,"红绿灯配置请求参数异常"),
        TRAFFIClIGHT_TIMEOUT_CODE(6005,"请求超时"),
        TRAFFIClIGHT_UNKNOW_CODE(4006,"未知错误"),

        TRAFFIClIGHT_CREATE_FAIL_CODE(6010,"红绿灯配置添加失败"),
        TRAFFIClIGHT_DEL_FAIL_CODE(6011,"红绿灯配置删除失败"),
        TRAFFIClIGHT_UPDATEA_FAIL_CODE(6012,"红绿灯配置更新失败"),
        TRAFFIClIGHT_GET_FAIL_CODE(6013,"红绿灯配置查询失败");

        private int code;
        private String message;
        SystemStatus(int code, String message) {
            this.code = code;
            this.message = message;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
