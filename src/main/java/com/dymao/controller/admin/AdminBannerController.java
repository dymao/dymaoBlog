package com.dymao.controller.admin;

import com.dymao.common.Utils.FileUtil;
import com.dymao.common.Utils.StringUtil;
import com.dymao.common.constants.Config;
import com.dymao.common.constants.Constant;
import com.dymao.model.Banner;
import com.dymao.service.BannerService;
import com.dymao.service.IdCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/3 18:32
 */
@Controller
@RequestMapping(value = "/admin/banner")
public class AdminBannerController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private BannerService bannerService;

    @Autowired
    private IdCreateService idCreateService;

    /**
     * 后台banner操作controller
     * @return
     */
    @RequestMapping(value = "/bannerList")
    public String adminIndex(Model model, HttpServletRequest request, HttpServletResponse response) {

        List<Banner> bannerList = bannerService.findAllBanner();
        model.addAttribute("bannerNum",bannerService.findAllBannerCount());
        model.addAttribute("bannerList",bannerList);
        return "admin/banner/banner-list";
    }

    /**
     * 跳转到轮播图新增页
     * @return
     */
    @RequestMapping(value = "/toAddPage",method = RequestMethod.GET)
    public String toAdminBannerAdd() {
        return "admin/banner/banner-add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Banner addBanner(Model model, Banner banner,HttpServletRequest request){
        banner.setId(idCreateService.getBannerId());
        banner.setDeleted(Constant.DELETE_FLAG_0);
        banner.setShowFlag(Constant.SHOW_FLAG_0);
        banner.setCreateTime(new Date());
        bannerService.addBanner(banner);
        removeBannerListFromContext(request);
        return banner;
    }

    /**
     * 跳转到轮播图修改页
     * @return
     */
    @RequestMapping(value = "/toEditPage/{id}",method = RequestMethod.GET)
    public String toAdminBannerEdit(@PathVariable String id,Model model,HttpServletRequest request) {
        Banner banner = bannerService.selectByPrimaryKey(id);
        model.addAttribute("banner",banner);
        return "admin/banner/banner-edit";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Banner updateBanner(Model model, Banner banner,HttpServletRequest request){
        banner.setCreateTime(new Date());
        bannerService.updateByPrimaryKeySelective(banner);
        removeBannerListFromContext(request);
        return banner;
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map delBanner(@PathVariable String id,HttpServletRequest request){
        Map result  = new HashMap();
        Integer count = bannerService.deleteByPrimaryKey(id);
        result.put("count",count);
        removeBannerListFromContext(request);
        return result;
    }

    @RequestMapping(value = "/delBatch",method = RequestMethod.POST)
    @ResponseBody
    public Map delBatchBanner(String bannerIds,HttpServletRequest request){
        Map result  = new HashMap();
        List<String> idList = Arrays.asList(bannerIds.split(","));
        Integer count = bannerService.deleteByBannerIds(idList);
        result.put("count",count);
        removeBannerListFromContext(request);
        return result;
    }

    @RequestMapping(value = "/showFlag/{opt}/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateShowFlagBanner(@PathVariable String id,@PathVariable String opt,HttpServletRequest request){
        Banner banner = new Banner();
        banner.setId(id);
        if("hide".equals(opt)){
            banner.setShowFlag(Constant.SHOW_FLAG_1);
        }else{
            banner.setShowFlag(Constant.SHOW_FLAG_0);
        }
        Integer count = bannerService.updateByPrimaryKeySelective(banner);
        removeBannerListFromContext(request);
        return count;
    }

    @RequestMapping(value = "/findBannerList",method = RequestMethod.GET)
    public List<Banner> findBannerList(Model model){
        return  bannerService.findBannerList();
    }

    /**
     * 友情链接信息变更时从application中移除列表，首页中会重新读取最新的链接列表
     * @param request
     */
    private void removeBannerListFromContext(HttpServletRequest request){
        ServletContext application = request.getServletContext();
        application.removeAttribute("bannerList");
    }
}
