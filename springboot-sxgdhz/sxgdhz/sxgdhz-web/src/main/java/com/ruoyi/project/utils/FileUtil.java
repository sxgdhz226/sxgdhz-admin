package com.ruoyi.project.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileUtil {
    // 输出内容到文件
    public static void writeStringToFile(String content, String filePath) {
        File file = new File(filePath);
        try {
            FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace(); // TODO
        }
    }
}
