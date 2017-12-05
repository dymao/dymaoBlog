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


    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public Map uploadImage(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        Map result  = new HashMap();
        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        String imgSuffix = originalFilename.substring(originalFilename.indexOf("."));
        String fileName = StringUtil.getCharAndNumr(20)+imgSuffix;
        String filePath = request.getSession().getServletContext().getRealPath(Config.BANNER_IMAGE_PAHT);
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {

        }
        result.put("url",Config.BANNER_IMAGE_PAHT+fileName);
        return result;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Banner addBanner(Model model, Banner banner){
        banner.setId(idCreateService.getBannerId());
        banner.setDeleted(Constant.DELETE_FLAG_0);
        banner.setShowFlag(Constant.SHOW_FLAG_0);
        banner.setCreateTime(new Date());
        bannerService.addBanner(banner);
        return banner;
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map delBanner(@PathVariable String id){
        Map result  = new HashMap();
        Integer count = bannerService.deleteByPrimaryKey(id);
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/delBatch",method = RequestMethod.POST)
    @ResponseBody
    public Map delBatchBanner(String bannerIds){
        Map result  = new HashMap();
        List<String> idList = Arrays.asList(bannerIds.split(","));
        Integer count = bannerService.deleteByBannerIds(idList);
        result.put("count",count);
        return result;
    }

    @RequestMapping(value = "/showFlag/{opt}/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateShowFlagBanner(@PathVariable String id,@PathVariable String opt){
        Banner banner = new Banner();
        banner.setId(id);
        if("hide".equals(opt)){
            banner.setShowFlag(Constant.SHOW_FLAG_1);
        }else{
            banner.setShowFlag(Constant.SHOW_FLAG_0);
        }
        Integer count = bannerService.updateByPrimaryKeySelective(banner);
        return count;
    }

    @RequestMapping(value = "/findBannerList",method = RequestMethod.GET)
    public List<Banner> findBannerList(Model model){
        return  bannerService.findBannerList();
    }
}
