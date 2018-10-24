package com.ruoyi.project.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.stream.FileImageOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@SuppressWarnings("all")
public class Regionldt {

	private static Logger log = LoggerFactory.getLogger(Regionldt.class);
	
    /**
     * 生成调用接口的请求路径和解析xml
     */
	public static void a() {
		SimpleDateFormat smf = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar cal  = Calendar.getInstance();
	        String fileUrl = CommonUtils.returnWebappsPath("")+ MessageFormat.format(ConfigsUtils.getConfigs().getRegionldtFplderPath(), DateUtil.getDateStr(DateUtil.ESPECIALLY_DATE_FORMAT));
	               fileUrl = fileUrl.replaceAll("\\\\", "/").replaceAll("///", "/").replace("/", File.separator);
//	               fileUrl = "D:\\uploadFile";//测试路径
	        try {
	        	cal.add(cal.MINUTE, -(cal.get(cal.MINUTE)%6));
	        	String endTime = smf.format(cal.getTime());
	        	cal.add(cal.MINUTE, -30);
	        	String startTime = smf.format(cal.getTime());
	        	String requestUrl = "http://172.22.1.175:80/di/image.action?userId=idc&pwd=U3cuYV&interfaceId=getRadarRegionImage&dataFormat=xml&ymdhms=["+startTime+"00-"+endTime+"00]&cols=fileName,imgbase64";
	        	String parms = getResultContent( requestUrl);
	            Document doc = DocumentHelper.parseText(parms);
	            Element root  = doc.getRootElement();
	            List<Element> listElement=root.elements();//所有一级子节点的list
	            //递归遍历当前节点所有的子节点  
	            for(int i = 1 ; i< listElement.size() ; i++){//遍历所有二级子节点  
            		List<Element> listSecondeleElement=listElement.get(i).elements();
            			Element fileNameElement  = listSecondeleElement.get(0);
            			Element pramElement   = listSecondeleElement.get(1);
            			String filename = fileNameElement.getTextTrim();
            			String parm  = pramElement.getTextTrim();
            			transcoding(parm,filename,fileUrl);
	            	}
	        	
	        } catch (Exception e) {
	        	log.error("执行结果================可见光云图暂无数据暂无数据");
	            e.printStackTrace();
	        }
			
	}
	
	/**
	 * 调用接口
	 * @param requestUrl
	 * @return
	 */
	public static String getResultContent(String requestUrl){
			StringBuilder result = new StringBuilder();
			URI uri = null;
	        URL url = null;
		    try {
			uri = new URI(requestUrl);
            url = uri.toURL();
            System.out.println(url);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(1000 * 60);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = br.readLine();
            if(line == null  || "".equals(line) || line.contains("error")){
	           	System.out.println("查询结果集为空，无法生成图片");
	           	return "";
           }
           while (line != null) {
               result.append(line);
               line = br.readLine();
           }
           return result.toString();
	    } catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * base64转码Byte数组
	 * @param parm
	 * @param filename
	 * @param fileUrl
	 */
	public static void transcoding(String parm, String filename, String fileUrl){
		 Base64ToImage b = new Base64ToImage();
        if (parm == null) {
        	return;
			} // 图像数据为空
			// Base64解码
			byte[] by =  b.decodeBase64ToBytes(parm);
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
	}
	/**
	 * 生成图片
	 * @param data
	 * @param path
	 */
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
