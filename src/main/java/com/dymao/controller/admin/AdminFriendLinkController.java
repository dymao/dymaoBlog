package com.dymao.controller.admin;

import com.dymao.model.FriendlyLink;
import com.dymao.service.FriendLinkService;
import com.dymao.service.IdCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/8 16:37
 */
@Controller
@RequestMapping(value = "/admin/friendlink")
public class AdminFriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private IdCreateService idCreateService;

    @RequestMapping(value = "/toLinklistPage",method = RequestMethod.GET)
    public String toLinklistPage(Model model){
        List<FriendlyLink> friendlinkList = friendLinkService.findFriendlinkList(new HashMap<>());
        int count = friendLinkService.findFriendlinkListCount(new HashMap<>());
        model.addAttribute("friendlinkList",friendlinkList);
        model.addAttribute("friendlinkListCount",count);
        return "admin/friendLink/friendlink";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map addFriendlink(Model model, FriendlyLink friendlyLink, HttpServletRequest request){
        Map result = new HashMap();
        String id = idCreateService.getFriendlinkId();
        String seq = id.substring(6,11);
        friendlyLink.setId(id);
        friendlyLink.setSn(Integer.parseInt(seq));
        friendlyLink.setCreateTime(new Date());
        int count = friendLinkService.insert(friendlyLink);
        result.put("count",count);
        result.put("friendlyLink",friendlyLink);
        removeFriendlinkListFromContext(request);
        return result;
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map delFriendLink(@PathVariable String id, HttpServletRequest request){
        Map result  = new HashMap();
        Integer count = friendLinkService.deleteByPrimaryKey(id);
        result.put("count",count);
        removeFriendlinkListFromContext(request);
        return result;
    }

    @RequestMapping(value = "/delBatch",method = RequestMethod.POST)
    @ResponseBody
    public Map delBatchFriendLink(String friendlinkIds, HttpServletRequest request){
        Map result  = new HashMap();
        List<String> friendlinkIdList = Arrays.asList(friendlinkIds.split(","));
        Integer count = friendLinkService.deleteByFriendlinkIds(friendlinkIdList);
        result.put("count",count);
        removeFriendlinkListFromContext(request);
        return result;
    }

    @RequestMapping(value = "/tofriendlinkEditPage/{friendlyLinkId}",method = RequestMethod.GET)
    public String toFriendLinkEditPage(Model model, @PathVariable("friendlyLinkId") String id){
        FriendlyLink friendlyLink = friendLinkService.selectByPrimaryKey(id);
        model.addAttribute("friendlyLink",friendlyLink);
        return "admin/friendLink/friendLink-edit";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map updateFridenlyLink(Model model,FriendlyLink friendlyLink, HttpServletRequest request){
        Map result = new HashMap();
        int count = friendLinkService.updateByPrimaryKey(friendlyLink);
        result.put("count",count);
        removeFriendlinkListFromContext(request);
        return result;
    }

    /**
     * 友情链接信息变更时从application中移除列表，首页中会重新读取最新的链接列表
     * @param request
     */
    private void removeFriendlinkListFromContext(HttpServletRequest request){
        ServletContext application = request.getServletContext();
        application.removeAttribute("friendlinkList");
    }
}
