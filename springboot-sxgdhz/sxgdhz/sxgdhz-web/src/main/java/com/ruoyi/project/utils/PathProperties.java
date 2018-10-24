/**  

* @Title: PathProperties.java

* @Package com.ssd.common.utils

* @Description: TODO(用一句话描述该文件做什么)

* @author A18ccms A18ccms_gmail_com  

* @date 2016-11-15 下午5:26:55

* @version V1.0  

*/
package com.ruoyi.project.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 *类描述：
 *@author: e450
 *@date： 日期：2016-11-15 时间：下午5:26:55
 *@version 1.0
 */
public class PathProperties {
	
	private static Map<String,String> myconfig=new HashMap<String, String>();
	
	/**
	 * 
	 * 获取数据当前网站的地址
	 **/
	public static String jeecmsPath(String pathName) {
		String config=myconfig.get(pathName);
		if(null !=config && !"".equals(config)){
			return config;
		}
		InputStream inputStream=null;
		ClassLoader cl = PathProperties. class .getClassLoader();
		if  (cl !=  null ) {        
		         inputStream = cl.getResourceAsStream( "ssdpath.properties" );        
		}  else {        
		         inputStream = ClassLoader.getSystemResourceAsStream( "ssdpath.properties" );
		}        
		Properties dbProps =  new Properties();
		try {
			dbProps.load(inputStream);
			inputStream.close();  
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String path = dbProps.getProperty(pathName);
		myconfig.put(pathName, path);
		return path;
	}
}
