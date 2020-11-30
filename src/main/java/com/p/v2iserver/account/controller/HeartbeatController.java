package com.p.v2iserver.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * TODO 健康检查
 * @author Pactera
 * @date 2020-11-19 09:54:53
 **/
@Controller
@RequestMapping("/account")
public class HeartbeatController {

    @RequestMapping("/heartbeat")
    @ResponseBody
    public String heartbeat(){
        return "heartbeat_account_server";
    }
}
