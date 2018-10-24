/**  

* @Title: CommonUtils.java

* @Package cn.net.plz.utils

* @Description: TODO(用一句话描述该文件做什么)

* @author A18ccms A18ccms_gmail_com  

* @date 2016-7-12 下午4:22:01

* @version V1.0  

*/
package com.ruoyi.project.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 *类描述：主要封装较为共同的方法
 *@author: e450
 *@date： 日期：2016-7-12 时间：下午4:22:01
 *@version 1.0
 */
public class CommonUtils {
	private static Map<String,Object> paramMap=new HashMap<String, Object>();
	/**
	 * 返回系统相对于tomcat的绝对路径
	 * 
	 * */
	public static String returnRealPath(String file){
		URL urlpath=CommonUtils.class.getResource("");
        String path=urlpath.toString();
        if(path.indexOf("WEB-INF")>0)  
        {  
            path=path.substring(0,path.indexOf("WEB-INF")-1);  
        }  
        path.replace("/", File.separator);
        path=path+"/"+file;
        path=path.substring(path.indexOf(":")+2, path.length());
        return path;
	}
	
	/**
	 * 返回系统相对于tomcat的绝对路径
	 * 
	 * */
	public static String returnWebappsPath(String file){
		URL urlpath=CommonUtils.class.getResource("");
        String path=urlpath.toString();
        if(path.indexOf("webapps")>0)  
        {  
            path=path.substring(0,path.indexOf("webapps")+7);  
        }  
        path.replace("/", File.separator);
        path=path+ File.separator+file;
        path=path.substring(path.indexOf(":")+2, path.length());
        return path;
	}
	
	
	public static Properties paraseProperties(String properName){
		    InputStream ins=CommonUtils.class.getResourceAsStream("/"+properName);
	        // 生成properties对象  
	        Properties p = new Properties();
	        try {  
	            p.load(ins);  
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }  
	       return p;
	}
	
	// 获取天气趋势
		public static String getQushiContent() {
			String zhText = "\\\\10.152.232.12\\serverspace\\share\\WeatherAnalysis.txt";// 天气趋势
			File file = new File(zhText);
			if (!file.exists()) {
				return "";
			}
			StringBuffer content = new StringBuffer("");
			try {
				InputStreamReader isr;
				isr = new InputStreamReader(new FileInputStream(file), "GBK");
				BufferedReader read = new BufferedReader(isr);
				String line = null;
				while ((line = read.readLine()) != null) {
					content.append(line);
				}
				read.close();
				isr.close();
			}catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return content.toString();
		}
		
		
		/**
		 * 获取properties 文件中的数据
		 * */
		public static String getConfigValue(String key){
			InputStream inputStream=null;
			ClassLoader cl = CommonUtils. class .getClassLoader();
			if  (cl !=  null ) {        
			         inputStream = cl.getResourceAsStream( "configs/properties/configs.properties" );        
			}  else {        
			         inputStream = ClassLoader.getSystemResourceAsStream( "configs/properties/configs.properties" );
			}        
			Properties dbProps =  new Properties();
			try {
				dbProps.load(inputStream);
				inputStream.close();  
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return dbProps.getProperty(key);
		}
		
		
		/**
		 * 
		 * 下载网络图片
		 * */
		public static void download(String urlString, String filename, String savePath) throws Exception {
	        // 构造URL  
	        URL url = new URL(urlString);
	        // 打开连接  
	        URLConnection con = url.openConnection();
	        // 输入流  
	        InputStream is = con.getInputStream();
	        con.setConnectTimeout(5*1000);  
	        // 1K的数据缓冲  
	        byte[] bs = new byte[1024];  
	        // 读取到的数据长度  
	        int len;  
	        // 输出的文件流  
	        File sf=new File(savePath);
	        if(!sf.exists()){  
	           sf.setWritable(true, false);
	           sf.mkdirs();  
	        }  
	        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
	        // 开始读取  
	        while ((len = is.read(bs)) != -1) {  
	          os.write(bs, 0, len);  
	        }  
	        // 完毕，关闭所有链接  
	        os.close();  
	        is.close();  
	        
	    }   
		
		
		/**
		 * 定时获取数据
		 * 
		 * */
	/*	public static void loadWeather(){
			
			String airportPerHourJson = HttpClienUtil.sendPost("http://10.152.252.58:8000/jeecms/web/showdetail","",null);	//获取机场当天startTime--endTime各小时数据
			airportPerHourJson = airportPerHourJson.replace("\"", "'");
			paramMap.remove("airportPerHourJson");
			paramMap.put("airportPerHourJson", airportPerHourJson);
		}*/
		
		/*public static void main(String[] args) {
			loadWeather();
		}*/
		/**
		 * 获取实时数据
		 * */
		
		/*public static void airSingleDay(){
			String airportJson = HttpClienUtil.sendGet("http://10.152.252.58:8000/jeecms/web/showdata", "", null);	//获取机场实时数据
			if(airportJson.contains("404")||airportJson.contains("返回上一页")){
				airportJson ="";
			}
			paramMap.remove("airportJson");
			paramMap.put("airportJson", airportJson);
			
		}*/
		/**
		 * 获取缓存里面的数据
		 * 
		 * */
		/*public static Object getAirportJson(){
			Object value=paramMap.get("airportPerHourJson")==null?"":paramMap.get("airportPerHourJson");
			if("".equals(value)||"[ ]".equals(value)){
				loadWeather();
			}
			value=paramMap.get("airportPerHourJson")==null?"":paramMap.get("airportPerHourJson").toString();
			return value.toString().replace("\"", "");
		}*/
		
		/**
		 * 获取单个节点数据
		 * */
		/*public static String getSingleAirWeather(){
			String value=paramMap.get("airportJson")==null?"":paramMap.get("airportJson").toString();
			if("".equals(value)||"[ ]".equals(value)){
				airSingleDay();
			}
			value=paramMap.get("airportJson")==null?"":paramMap.get("airportJson").toString();
			return value;
		}*/

		/**
		
		* @Title: chaoxi
		* @Description: 潮汐
		* @param     设定文件
		* @return void    返回类型
		* @throws
		*/
		/*public static void chaoxi() {
			String chaoxi = HttpClienUtil.sendGet("http://10.152.252.58:8000/jeecms/web/chaoxi", "", null);	//获取机场实时数据
			if(chaoxi.contains("404")||chaoxi.contains("返回上一页")){
				chaoxi ="";
			}
			paramMap.remove("chaoxi");
			paramMap.put("chaoxi", chaoxi);
			
		}*/

		/**
		 * 获取单个节点数据
		 * */
		/*public static Object getchaoxi(){
			String value=paramMap.get("chaoxi")==null?"":paramMap.get("chaoxi").toString();
			if("".equals(value)||"[ ]".equals(value)){
				chaoxi();
			}
			value=paramMap.get("chaoxi")==null?"":paramMap.get("chaoxi").toString();
			return value;
		}*/
		
		/**
		 * 
		 * 数据转换
		 * */
		public static String change(int num){
			String desc="";
			if(num<=2){
				desc=num+"/最弱";
			}else if(num <=4){
				desc=num+"/弱";
			}else if(num <=6){
				desc=num+"/中等";
			}else if(num <=9){
				desc=num+"/强";
			}else{
				desc=num+"/很强";
			}
			return desc;
		}
		
		// Clob类型 转String  
	    public static String ClobToString(Clob clob) throws SQLException, IOException {
	      String reString = "";
	      Reader is = clob.getCharacterStream();// 得到流
	      BufferedReader br = new BufferedReader(is);
	      String s = br.readLine();
	      StringBuffer sb = new StringBuffer();
	      while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING  
	          sb.append(s);  
	          s = br.readLine();  
	      }  
	      reString = sb.toString();  
	      if(br!=null){  
	          br.close();  
	      }  
	      if(is!=null){  
	          is.close();  
	      }  
	      return reString;  
	     }  
		
}
