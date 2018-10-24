package com.ruoyi.project.websocket.controller;

import com.ruoyi.project.websocket.model.RequestMessage;
import com.ruoyi.project.websocket.model.ResponseMessage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ws")
public class WsController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage requestMessage) {
        System.out.println(requestMessage.getName());
        return new ResponseMessage("welcome " + requestMessage.getName());
    }

    @RequiresPermissions("ws:wsPage:view")
    @GetMapping("wsPage")
    public String wsPage() {
        return "ws/ws";
    }

}
