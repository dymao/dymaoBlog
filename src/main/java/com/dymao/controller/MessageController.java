package com.dymao.controller;

import com.dymao.common.Utils.ImageTokenUtil;
import com.dymao.common.constants.Constant;
import com.dymao.common.helper.sensitive.SensitivewordFilter;
import com.dymao.model.Message;
import com.dymao.service.IdCreateService;
import com.dymao.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-20 23:53
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private MessageService messageService;

    @Autowired
    private IdCreateService idCreateService;

    @Autowired
    private SensitivewordFilter sensitivewordFilter;

    @GetMapping(value= "/list")
    public String list(Model model, String pageNum, String pageSize) {
        if(StringUtils.isEmpty(pageNum) || !StringUtils.isNumeric(pageNum)){
            pageNum = "1";
        }
        if(StringUtils.isEmpty(pageSize) || !StringUtils.isNumeric(pageSize)){
            pageSize = "10";
        }
        Map paramMap = new HashMap();
        paramMap.put("showflag", Constant.SHOW_FLAG_0);
        getMessageList(pageNum,pageSize,paramMap,model);
        return "message/message-list";
    }

    @PostMapping(value = "/add")
    public String list(Model model,Message message,String checkCode,HttpServletRequest request) {

        boolean validationFlag = true;
        String resultPage = "message/message-list";
        if(StringUtils.isEmpty(checkCode)){
            model.addAttribute("errorStatus",3);
            validationFlag = false;
        }
        int checkResult = ImageTokenUtil.vrifyImageToken(checkCode,request);
        if(checkResult == 1 || checkResult == 2){ // 失效
            model.addAttribute("errorStatus",checkResult);
            validationFlag = false;
        }
        if(StringUtils.isEmpty(message.getNickName())){
            model.addAttribute("nickNameErrorMsg","昵称不能为空");
            validationFlag = false;
        }
        if(StringUtils.isEmpty(message.getContent())){
            model.addAttribute("contentErrorMsg","评论内容不能为空");
            validationFlag = false;
        }
        if(validationFlag){
            // 加入次数及敏感词判断
           String newContent =  sensitivewordFilter.replaceSensitiveWord(message.getContent(),SensitivewordFilter.minMatchTYpe,"*");
            String MessageId = idCreateService.getMessageId();
            message.setCreateTime(new Date());
            message.setId(MessageId);
            message.setContent(newContent);
            messageService.insert(message);
        }
        String pageNum = "1";
        String pageSize = "10";
        Map paramMap = new HashMap();
        paramMap.put("showflag", Constant.SHOW_FLAG_0);
        getMessageList(pageNum,pageSize,paramMap,model);
        return resultPage;
    }

    private List<Message> getMessageList(String pageNum,String pageSize,Map paramMap,Model model){
        PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<Message> messageList = messageService.findAllByCondition(paramMap);
        PageInfo<Message> pageInfo = new PageInfo(messageList);
        model.addAttribute("pageInfo",pageInfo);
        return messageList;
    }


}
