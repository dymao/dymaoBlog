package com.dymao.controller;

import com.dymao.common.Utils.DateUtils;
import com.dymao.common.Utils.FileUtil;
import com.dymao.common.Utils.StringUtil;
import com.dymao.common.constants.Config;
import com.dymao.common.constants.Dict;
import com.dymao.vo.BaseMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-10 17:08
 */
@RestController
@RequestMapping("/admin/fileUpload")
public class FileUploadController {

    //获取配置文件中Windows环境下图片的路径
    @Value("${win_upload.path}")
    private String winImagesPath;

    //获取配置文件中linux环境下图片的路径
    @Value("${linux_upload.path}")
    private String linuxImagesPath;


    @PostMapping(value = "/bannerImg",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map bannerImg(@RequestParam("file") MultipartFile file){
        Map result  = new HashMap();
        if (!file.isEmpty() && file.getContentType().contains("image")) {
            String webUploadPath = getWebUploadPath();
            String savePath = FileUtil.uploadFile(file,webUploadPath, Config.BANNER_IMAGE_PATH,StringUtil.getCharAndNumr(20));
            if(StringUtils.isNotEmpty(savePath)){
                result.put("url",File.separator+savePath.replaceAll("\\\\", "/"));
            }
        }
        return result;
    }

    @PostMapping(value = "/blogTitleImg",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map blogTitleImg(@RequestParam("titleImg") MultipartFile file){
        Map result  = new HashMap();
        if (!file.isEmpty() && file.getContentType().contains("image")) {
            String webUploadPath = getWebUploadPath();
            String savePath = FileUtil.uploadFile(file,webUploadPath, Config.BLOG__TITLE_IMAGE_PATH,StringUtil.getCharAndNumr(20));
            if(StringUtils.isNotEmpty(savePath)){
                result.put("url",File.separator+savePath.replaceAll("\\\\", "/"));
            }
        }
        return result;
    }

    @PostMapping(value = "/blogImg",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map blogImg(@RequestParam("blogImg") MultipartFile file){
        Map result  = new HashMap();
        if (!file.isEmpty() && file.getContentType().contains("image")) {
            String webUploadPath = getWebUploadPath();
            String savePath = FileUtil.uploadFile(file,webUploadPath, Config.BLOG__CONTENT_IMAGE_PATH,StringUtil.getCharAndNumr(20));
            if(StringUtils.isNotEmpty(savePath)){
                result.put("errno",0);
                List<String> list = new ArrayList<String>(1);
                list.add(File.separator+savePath.replaceAll("\\\\", "/"));
                result.put("data",list);
            }
        }
        return result;
    }

    private String getWebUploadPath(){
        String webUploadPath = "";
        String OSName = System.getProperty(Dict.OS_NAME).toLowerCase();
        if(OSName.indexOf("windows")>=0){
            webUploadPath = winImagesPath;
        }else{
            webUploadPath = linuxImagesPath;
        }
        return  webUploadPath;
    }
}
