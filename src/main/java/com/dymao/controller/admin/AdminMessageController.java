package com.dymao.controller.admin;

import com.dymao.model.Message;
import com.dymao.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Mervin
 * @Description:
 * @date 2018-03-06 23:36
 */
@Controller
@RequestMapping("/admin/message")
public class AdminMessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/toMsgListPage")
    public String toMsgListPage(String pageNum, String pageSize, Map paramMap, Model model){

        //PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<Message> messageList = messageService.findAllByCondition(paramMap);

        int count = messageService.findAllByConditionCount(paramMap);
        //PageInfo<Message> pageInfo = new PageInfo(messageList);
        model.addAttribute("messageList",messageList);
        model.addAttribute("messageNum",count);
        return "admin/message/message";
    }

    @GetMapping(value = "/toReplyPage/{msgId}")
    public String toMsgReplyPage(@PathVariable String msgId, Model model){

       Message message = messageService.selectByPrimaryKey(msgId);

        model.addAttribute("message",message);
        return "admin/message/message-reply";
    }

    @PostMapping(value = "/reply")
    @ResponseBody
    public Map updateCategory(Model model,Message message){
        Map result = new HashMap();
        message.setReplyTime(new Date());
        int count = messageService.updateByPrimaryKey(message);
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map delBanner(@PathVariable String id){
        Map result  = new HashMap();
        Integer count = messageService.deleteByPrimaryKey(id);
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/delBatch",method = RequestMethod.POST)
    @ResponseBody
    public Map delBatchCategory(String messageIds){
        Map result  = new HashMap();
        List<String> messageIdsIdList = Arrays.asList(messageIds.split(","));
        Integer count = messageService.deleteByMessageIds(messageIdsIdList);
        result.put("count",count);
        return result;
    }
}
