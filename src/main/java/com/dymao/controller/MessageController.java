package com.dymao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-20 23:53
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @RequestMapping(value = "/list")
    public String list() {
        return "message/message-list";
    }

}
