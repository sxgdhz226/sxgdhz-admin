package com.ruoyi.project.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("all")
public class CopyLdtUtil {
	
	public static void CopyLdt(){
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat smp = new SimpleDateFormat("yyyyMMdd.HHmm");
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
		Calendar cal  = Calendar.getInstance();
		String creatFilePath = CommonUtils.returnWebappsPath("ldt")+"/radar/30/"+sm.format(cal.getTime());
			try {
				loop1:for (int i = 0; i < 8; i++) {
					    String dateTime;
					    cal.add(cal.MINUTE, -(cal.get(cal.MINUTE)%6));
					    if(i == 0){
					    	  dateTime = smf.format(new Date(cal.getTimeInMillis()));
					    }else{
					    	cal.add(cal.MINUTE,-6);
					    	  dateTime = smf.format(new Date(cal.getTimeInMillis()));
					    }
						 String Url = "http://10.148.83.228:8922/dataunit/temporary/renderTemporaryData?datetime="+dateTime+":00&type=swan&element=cappi&time=0&level=3&top=25.000&bottom=19.000&left=107.000&right=118.000&width=1050&height=880";
						 Url=Url.replace(" ", "%20");
//						 System.out.println(Url);
						 byte[] b= getImageFromNetByUrl(Url);
						 if( b!=null){
							 File file =new File(creatFilePath);
					            file.getParentFile().mkdirs();
					            if(!file.exists()) {  
					            //  先创建文件所在的目录
					            	file.mkdirs();
					            }
					            String filePaht =creatFilePath+"/"+smp.format(cal.getTime())+"00.02.19.758.PNG";
					            File fileaddress = new File(filePaht);
					            if(fileaddress.exists()){
					            	System.out.println("雷达图已存在 ----》"+filePaht);
					            	continue loop1 ;
					            }
					            System.out.println("雷达图路径-------------》"+filePaht);
				            	writeImageToDisk(b, filePaht);
				            	System.out.println("通过HTTP接口下载雷达图");
						 }else{
							 System.out.println("雷达图接口暂无没有数据");
					 }
				}
	        } catch (Exception e) {
//	            e.printStackTrace();
	        	System.out.println("雷达图接口暂无没有数据");
	        }
		}
	
	public static void writeImageToDisk(byte[] img,String fileName){
		try {
			File file = new File(fileName);
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static byte[] getImageFromNetByUrl(String strUrl){
		try {
			URL url = new URL(strUrl);
			 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			 conn.setRequestMethod("GET");
			 conn.connect();
			 System.out.println("请求状态码:"+conn.getResponseCode());
			 if(200!= conn.getResponseCode()){
				 return null;
			 }
			 InputStream input =  conn.getInputStream();
			 byte[] Image = readInputStream(input);
			 return Image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] readInputStream(InputStream inStream){
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while((len = inStream.read(buffer)) != -1){
			 outStream.write(buffer,0 ,len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outStream.toByteArray();
	}
	
}
