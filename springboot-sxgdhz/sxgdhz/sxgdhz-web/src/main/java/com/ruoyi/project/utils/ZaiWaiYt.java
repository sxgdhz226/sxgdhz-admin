package com.ruoyi.project.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ZaiWaiYt {
	private static Logger log = LoggerFactory.getLogger(ZaiWaiYt.class);

	public String a() {
		SimpleDateFormat smf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal  = Calendar.getInstance();
		String ziWaiUrl = CommonUtils.returnWebappsPath("wxYt/ziwai/")+smf.format(cal.getTime());
		//设置比时间少一个小时
		cal.add(cal.HOUR_OF_DAY,-7);
		Base64ToImage b = new Base64ToImage();
		String data, filename;
		String srv = "172.22.1.175";
		loop1:for (int i = 0; i <= 8; i++) {
			//获取结束小时
			int  enHours = cal.get(cal.HOUR_OF_DAY);
			//获取结束日期
			Date enDate  = new Date(cal.getTimeInMillis());
			//在结束时间上减一个小时
			cal.add(cal.HOUR_OF_DAY,-1);
			int staHours = cal.get(cal.HOUR_OF_DAY);
			//获取开始时间日期
			Date staDate  = new Date(cal.getTimeInMillis());
			String timeIntervar = smf.format(staDate)+ String.format("%02d",staHours )+"0000-"+smf.format(enDate)+ String.format("%02d", enHours)+"0000";
			String parms = "userId=idc&pwd=U3cuYV&interfaceId=getInfraredCloudImage_PJ2&dataFormat=xml2&ymdhms=["+timeIntervar+"]&cols=fileName,fileUrl,suffix,width,height,size,imgbase64";
			String xmldata = b.getHttpData(srv, parms);
			try {
				Document doc = DocumentHelper.parseText(xmldata);
				data = doc.selectSingleNode("DS/R/IMGBASE64").getText();
				filename = doc.selectSingleNode("DS/R/FILENAME").getText();
				byte[] decode = b.decodeBase64ToBytes(data);
				File file = new File(ziWaiUrl);
				if (!file.exists() && !file.isDirectory()) {
					file.setWritable(true, false);
					file.mkdirs();
				}
				String fileaddress = ziWaiUrl+"/"+ filename;
				File fileExists = new File(fileaddress.trim());
				if (fileExists.exists()) {
					System.out.println("紫外线云图已存在----》"+fileaddress);
				 continue loop1;
				}
				byte2image(decode, fileaddress);
			} catch (Exception e) {
				log.error("执行结果================紫外线云图暂无数据暂无数据");
			}
		}

		return "";
	}
	
	  public void byte2image(byte[] data, String path) {
	        if (data.length < 3 || path.equals("")) {
	            return ;
	        }
	        try {
	            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
	            imageOutput.write(data, 0, data.length);
	            imageOutput.close();
	            log.info("执行结果===============紫外线云图下载成功");
	            System.out.println("Make Picture success,Please find image in " + path);

	        } catch (Exception ex) {
	            System.out.println("Exception: " + ex);
	            ex.printStackTrace();

	        }
	    }

}
