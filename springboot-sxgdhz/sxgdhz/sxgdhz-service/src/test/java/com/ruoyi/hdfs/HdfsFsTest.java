//package com.ruoyi.hdfs;
//
//
//import com.ruoyi.service.hadoop.utils.HdfsUtils;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IOUtils;
//
//public class HdfsFsTest {
//
//    public static void read() throws Exception {
//        String fileUri = "/user/hyman/mr/input/wordcount";
//        FileSystem fileSystem = HdfsUtils.getFileSystem();
//        FSDataInputStream inStream = fileSystem.open(new Path(fileUri));
//
//        try {
//            IOUtils.copyBytes(inStream, System.out, 4096, false);
//        } catch (Exception e) {
//
//        } finally {
//            IOUtils.closeStream(inStream);
//        }
//    }
//}
