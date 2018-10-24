package com.ruoyi.project.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class Shengldt02 {

	private static Logger log = LoggerFactory.getLogger(Shengldt02.class);

	@SuppressWarnings("unused")
	public static void a() {
		Base64ToImage b = new Base64ToImage();
		StringBuilder result = new StringBuilder();
		SimpleDateFormat smf = new SimpleDateFormat("yyyyMMddHH");
		SimpleDateFormat smp = new SimpleDateFormat("yyyyMMdd.HH");
		Calendar cal  = Calendar.getInstance();
	        URI uri = null;
	        URL url = null;
	        String fileUrl = CommonUtils.returnWebappsPath("")+ MessageFormat.format(ConfigsUtils.getConfigs().getShengldtFplderPath02(), DateUtil.getDateStr(DateUtil.ESPECIALLY_DATE_FORMAT));
	               fileUrl = fileUrl.replaceAll("\\\\", "/").replaceAll("///", "/").replace("/", File.separator);
	        try {
	        	String endTime = smf.format(cal.getTime());
	        	cal.add(cal.HOUR, -1);
	        	String startTime = smf.format(cal.getTime());
	        	String parms = "http://172.22.1.175/di/http.action?userId=idc&pwd=U3cuYV&interfaceId=getRACSwanTimeRangeRADAMOSAIC&dataFormat=image&s_ymdhms="+startTime+"0000&e_ymdhms="+endTime+"0000&level=1&i_width=1050&i_height=880";
	            uri = new URI(parms);
	            url = uri.toURL();
	            System.out.println(url);
	            URLConnection conn = url.openConnection();
	            conn.setConnectTimeout(1000 * 60);
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	            String line = br.readLine();
	            if(line.contains("error")){
	            	System.out.println("查询结果集为空，无法生成图片");
	            	return;
	            }
	            while (line != null) {
	                result.append(line);
	                line = br.readLine();
	            }
	            JSONObject json = JSONObject.fromObject(result.toString());
	            if (json.get("img") == null) {
	            	return; // 图像数据为空
	            }
	            String parm = json.get("img").toString();
	            // Base64解码
				byte[] by =  b.decodeBase64ToBytes(parm);
				String filename = smp.format(cal.getTime())+"0000.02.19.758.PNG";
				File targetfileUrl = new File(fileUrl);
				if (!targetfileUrl.exists()) {
					targetfileUrl.mkdirs();
				}
				
				String filePath = fileUrl + File.separator+ filename;
				File targetfilePath = new File(filePath);
				if(targetfilePath.exists()){
					System.out.println("文件已存在 === 》 "+filePath);
					return;
				}
				byte2image(by, filePath);
	        	
	        } catch (Exception e) {
	        	log.error("执行结果================可见光云图暂无数据暂无数据");
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
            log.info("执行结果===============省局雷达图下载成功");
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
		a();
	}
}
