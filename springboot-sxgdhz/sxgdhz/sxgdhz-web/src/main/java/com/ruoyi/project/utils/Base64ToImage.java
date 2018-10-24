package com.ruoyi.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author 李卫东
 *      采集卫星云图
 */
public class Base64ToImage {
	private static Logger log =  LoggerFactory.getLogger(Base64ToImage.class);
	
  
    public byte[] decodeBase64ToBytes(String s) {
        if (s == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    public String getHttpData(String srv, String parm) {
        StringBuilder result = new StringBuilder();
        URI uri = null;
        URL url = null;
        try {
            uri = new URI("http", srv, "/di/image.action", parm, "");
            url = uri.toURL();
            System.out.println(url);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(1000 * 60);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = br.readLine();
            while (line != null) {
                result.append(line);
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
    
    

//	 @RequestMapping(value="/restful",produces="text/plain;charset=UTF-8")
//	    public @ResponseBody String info(String data) {
//		 HttpServletRequest request = null;
//		String filePath = request.getSession().getServletContext().getRealPath("");
//	    	System.out.println("进ajax的action了 filePath");  
//	        return "Hello World!";
//	    }
//	
//	//保存等值线到服务端（利用解析base64数据转为图片）
//	@ResponseBody
//	@RequestMapping(value = "/createLineImg")
//		public void createLineImg(
//				String data,
//				String element,
//				String type) {
//		
//			HttpServletRequest request = null ;
//			String base64Str = data;
//			String secondDir = "";
//			if ("line".equals(type)) {
//				secondDir = "isoline";
//			} else if ("surface".equals(type)) {
//				secondDir = "isosurface";
//			}
//			try {			
//				// 获取当前地区编码		
//				if (base64Str == null) {
//					return;
//				} // 图像数据为空
//				BASE64Decoder decoder = new BASE64Decoder();
//				// Base64解码
//				byte[] b = decoder.decodeBuffer(base64Str.substring(22));
//				// 生成jpeg图片
////				String filePath = request.getSession().getServletContext()
////						.getRealPath("")
////						+ "\\uploadFile\\"
////						+ secondDir + "\\" + element + "\\";
//				String filePath = "D:\\uploadFile\\"+ secondDir+"\\";
//				String filename = String.valueOf(new Date().getTime());
//				File targetPath = new File(filePath);
//				if (!targetPath.exists()) {
//					targetPath.mkdirs();
//				}
//				File targetFile = new File(filePath + filename + ".png");
//				if (!targetFile.exists()) {
//					targetFile.createNewFile();
//				}
//				OutputStream out = new FileOutputStream(targetFile);
//				out.write(b);
//				out.flush();
//				out.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
		
	}
}