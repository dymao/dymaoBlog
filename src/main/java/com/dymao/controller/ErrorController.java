package com.dymao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/7 23:21
 * 自定义错误页面覆盖spring boot中的错误页面
 * @author yinjihuan
 *
 */
@Controller
public class ErrorController {

    @GetMapping("/400")
    public String badRequest() {
        return "error/400";
    }

    @GetMapping("/404")
    public String notFound() {
        return "error/404";
    }

    @GetMapping("/500")
    public String serverError() {
        return "error/500";
    }
}

