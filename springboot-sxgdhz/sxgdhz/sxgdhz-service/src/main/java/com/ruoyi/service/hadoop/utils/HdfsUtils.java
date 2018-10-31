package com.ruoyi.service.hadoop.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

public class HdfsUtils {

    public static FileSystem getFileSystem() throws Exception {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(configuration);
        return fileSystem;
    }
}
