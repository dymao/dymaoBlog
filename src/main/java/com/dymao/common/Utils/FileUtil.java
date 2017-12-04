package com.dymao.common.Utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Mervin
 * @Description:
 * @date 2017/12/3 22:29
 */
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
