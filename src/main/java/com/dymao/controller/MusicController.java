package com.dymao.controller;

import com.dymao.common.constants.Constant;
import com.dymao.vo.BlogVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-18 22:28
 */
@Controller
@RequestMapping(value = "/music")
public class MusicController {
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model) {
        return "music/music";
    }
}
