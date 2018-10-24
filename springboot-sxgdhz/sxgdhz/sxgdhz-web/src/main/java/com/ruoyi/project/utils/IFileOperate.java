package com.ruoyi.project.utils;

import jcifs.smb.SmbFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件操作类
 * @author 崔永辉
 * @since 2010-3-19
 * @version 1.0
 */
public interface IFileOperate {
	/**
	 * 删除文件
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @param filePath 文件地址
	 * @return boolean
	 */
	public boolean deleteFile(String filePath);

	/**
	 * 删除目录
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @param directoryPath 目录地址
	 * @return boolean
	 */
	public boolean deleteDirectory(String directoryPath);

	/**
	 * 清除目录中的文件
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @param directoryPath 目录地址
	 * @return boolean
	 */
	public boolean clearDirectory(String directoryPath);

	/**
	 * 文件拷贝
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @param sourceFilePath 源文件
	 * @param targetFilePath 目标文件
	 * @return boolean
	 */
	public boolean copyFile(String sourceFilePath, String targetFilePath);

	/**
	 * 文件拷贝
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @return boolean
	 */
	public boolean copyFile(File sourceFile, File targetFile);

	/**
	 * 拷贝目录
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @param sourceDir
	 * @param targetDir
	 * @return boolean
	 */
	public boolean copyDirectory(File sourceDir, File targetDir);

	/**
	 * 拷贝目录
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @param sourceDirPath
	 * @param targetDirPath
	 * @return boolean
	 */
	boolean copyDirectory(String sourceDirPath, String targetDirPath);

	/**
	 * 创建文件目录
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @param directoryPath 目录地址
	 * @return boolean
	 */
	public boolean createDirectory(String directoryPath);

	/**
	 * 创建文件
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @param filePath
	 * @return boolean
	 */
	public boolean createFile(String filePath);

	/**
	 * 文件是否存在
	 * @since 2010-3-19<br/>
	 *        崔永辉
	 * @param filePath
	 * @return boolean
	 */
	public boolean fileExists(String filePath);

	/**
	 * 读取文本文件的内容 <br/>
	 * 创建人: 崔永辉
	 * @since 2010-5-13 下午05:02:09
	 * @param textFile
	 * @return
	 */
	public String getFileContent(File textFile);
	
	/**
	 * 访问共享文件
	 * @param smbMachine 共享享机器的文件,如smb://xxx:xxx@10.108.23.112/myDocument/测试文本.txt,xxx:xxx是共享机器的用户名密码  
	 * @return
	 */
	public SmbFile listDirFromSmb(String smbMachine);
	/**
	 * 生产缩略图
	 * 崔永辉
	 * @param sourceFile 待处理的共享图片
	 * @param targetFilePath 生成缩略图目标文件
	 * @param width 宽
	 * @param hight 高
	 * @throws Exception
	 * @return boolean
	 */
	public boolean saveImageAsJpg(SmbFile sourceFile, String targetFilePath, int width, int hight) throws Exception;
	
	/**
	 * 传入一个文件(File)对象，检查文件编码
	 * @param file
	 *        File对象实例
	 * @return 文件编码，若无，则返回null
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String guestFileEncoding(File file) throws FileNotFoundException, IOException;

	/**
	 * 获取文件的编码
	 * @param path
	 *        文件路径
	 * @return 文件编码，eg：UTF-8,GBK,GB2312形式，若无，则返回null
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String guestFileEncoding(String path) throws FileNotFoundException, IOException;
}
