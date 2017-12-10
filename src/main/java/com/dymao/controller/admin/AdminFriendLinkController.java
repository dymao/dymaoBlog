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
    public Map addFriendlink(Model model, FriendlyLink friendlyLink){
        Map result = new HashMap();
        String id = idCreateService.getFriendlinkId();
        String seq = id.substring(6,11);
        friendlyLink.setId(id);
        friendlyLink.setSn(Integer.parseInt(seq));
        friendlyLink.setCreateTime(new Date());
        int count = friendLinkService.insert(friendlyLink);
        result.put("count",count);
        result.put("friendlyLink",friendlyLink);
        return result;
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map delBanner(@PathVariable String id){
        Map result  = new HashMap();
        Integer count = friendLinkService.deleteByPrimaryKey(id);
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/delBatch",method = RequestMethod.POST)
    @ResponseBody
    public Map delBatchCategory(String friendlinkIds){
        Map result  = new HashMap();
        List<String> friendlinkIdList = Arrays.asList(friendlinkIds.split(","));
        Integer count = friendLinkService.deleteByFriendlinkIds(friendlinkIdList);
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/tofriendlinkEditPage/{friendlyLinkId}",method = RequestMethod.GET)
    public String toCategoryEditPage(Model model, @PathVariable("friendlyLinkId") String id){
        FriendlyLink friendlyLink = friendLinkService.selectByPrimaryKey(id);
        model.addAttribute("friendlyLink",friendlyLink);
        return "admin/friendLink/friendLink-edit";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Map updateCategory(Model model,FriendlyLink friendlyLink){
        Map result = new HashMap();
        int count = friendLinkService.updateByPrimaryKey(friendlyLink);
        result.put("count",count);
        return result;
    }
}
