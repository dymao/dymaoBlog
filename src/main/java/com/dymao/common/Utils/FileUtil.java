package com.dymao.common.Utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/3 22:29
 */
public class FileUtil {
    public static String uploadFile(MultipartFile file,String webUploadPath, String filePath, String fileName){
        try {
            // 获取图片的文件名
            String originalFilename = file.getOriginalFilename();
            // 获取图片的扩展名
            String extensionName = StringUtils.substringAfter(originalFilename, ".");
            // 新的图片文件名 = 获取时间戳+"."图片扩展名
            String newFileName = fileName + "." + extensionName;
            // 文件路径
            String saveFilePath = webUploadPath.concat(filePath);

            File dest = new File(saveFilePath, newFileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            // 上传到指定目录
            file.transferTo(dest);
            // 将反斜杠转换为正斜杠
            return filePath.replaceAll("\\\\", "/") + newFileName;
        } catch (IOException e) {
            System.err.println("上传文件出现异常："+e);
        }
        return  null;
    }
}
