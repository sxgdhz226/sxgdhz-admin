package com.ruoyi.project.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.imageio.stream.FileImageOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Shengldt {

	private static Logger log = LoggerFactory.getLogger(Shengldt.class);

	public static void a() {
		Base64ToImage b = new Base64ToImage();
		StringBuilder result = new StringBuilder();
		SimpleDateFormat smf = new SimpleDateFormat("yyyyMMddHHmm");
		SimpleDateFormat smp = new SimpleDateFormat("yyyyMMdd.HHmm");
		Calendar cal  = Calendar.getInstance();
	        URI uri = null;
	        URL url = null;
	        String fileUrl = CommonUtils.returnWebappsPath("")+ MessageFormat.format(ConfigsUtils.getConfigs().getShengldtFplderPath01(), DateUtil.getDateStr(DateUtil.ESPECIALLY_DATE_FORMAT));
	               fileUrl = fileUrl.replaceAll("\\\\", "/").replaceAll("///", "/").replace("/", File.separator);
//	               fileUrl = "D:\\uploadFile";//测试路径
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
//	    	String parms = "http://172.22.1.175/di/http.action?interfaceId=getRACSwanRADAMOSAIC&userId=gmcrzh&Pwd=zhuh123&dataFormat=json&ymdhms=20171116000000&level=3";
//	    	String parms = "http://172.22.1.175/di/http.action?userId=idc&pwd=U3cuYV&interfaceId=getRACSwanTimeRangeRADAMOSAIC&dataFormat=image&s_ymdhms=20171219000000&e_ymdhms=20171219000000&level=1&i_width=884&i_height=610";
	        	String parms = "http://172.22.1.175/di/http.action?userId=idc&pwd=U3cuYV&interfaceId=getRACSwanRADAMOSAIC&dataFormat=image&ymdhms="+dateTime+"00&level=3&i_width=1050&i_height=1050";
	            uri = new URI(parms);
	            url = uri.toURL();
	            System.out.println(url);
	            URLConnection conn = url.openConnection();
	            conn.setConnectTimeout(1000 * 60);
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	            String line = br.readLine();
	            if(line.contains("error")){
	            	System.out.println("查询结果集为空，无法生成图片");
	            	continue loop1;
	            }
	            while (line != null) {
	                result.append(line);
	                line = br.readLine();
	            }
	            JSONObject json = JSONObject.fromObject(result.toString());
	            JSONArray jsonArray = JSONArray.fromObject(json.get("DATA"));
	            if(jsonArray.getJSONObject(0).get("img") == null){
	            	continue loop1;
	            } // 图像数据为空
	            String parm = jsonArray.getJSONObject(0).get("img").toString();
				BASE64Decoder decoder = new BASE64Decoder();
				// Base64解码
				byte[] by =  b.decodeBase64ToBytes(parm);
				String filename = smp.format(cal.getTime())+"00.02.19.758.PNG";
				File targetfileUrl = new File(fileUrl);
				if (!targetfileUrl.exists()) {
					targetfileUrl.mkdirs();
				}
				
				String filePath = fileUrl + File.separator+ filename;
				File targetfilePath = new File(filePath);
				if(targetfilePath.exists()){
					System.out.println("文件已存在 === 》 "+filePath);
					continue loop1;
				}
				System.out.println("111");
				byte2image(by, filePath);
	        	}
	        } catch (Exception e) {
	        	log.error("执行结果================可见省雷达透明图01暂无数据");
	            e.printStackTrace();
	        }
			
	}
	
    public static void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) {
            return ;
        }
        FileImageOutputStream imageOutput = null;
        try {
            imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            log.info("执行结果===============省雷达透明图01下载成功");
            System.out.println("Make Picture success,Please find image in " + path);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();

        }finally{
        	if(imageOutput!=null){
        		try {
        			imageOutput.close();
        		} catch (IOException e) {
        		}
        	}
        }
    }
	
	public static void main(String[] args) {
//		a();
	}
}
