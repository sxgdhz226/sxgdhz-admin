package com.ruoyi.project.web.controller;

import com.ruoyi.project.utils.DateUtils;
import com.ruoyi.project.utils.FileOperateImpl;
import net.sf.jsqlparser.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/entry")
public class EntryController {

    /**
    * 生成报文
    * @throws IOException
    */
    @RequestMapping(value="/makeReport")
    @ResponseBody
	public Object makeReport(@RequestParam Map<String,Object> paramMap, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException {
		String filepath = "";
		String rootPath = request.getServletContext().getRealPath("/");
		// 判断模板是否存在
		FileOperateImpl foi = new FileOperateImpl();
		String code="BFZH";
		String fileend=".FZH";
			filepath = rootPath + "uploadfile/alarmPlan/warningreport.FZQ";
			File sourceFile = new File(filepath);
			String timeString = DateUtils.formaterLocalDateTime14(LocalDateTime.now())+new Random().nextInt();
			timeString = timeString.replace("-", "");
			File newPathFile = new File(rootPath + "uploadfile/officefile/report/" + timeString);
			if(!newPathFile.exists())
			{
				newPathFile.mkdirs();
			}
			String publishDate =String.valueOf(paramMap.get("relieasetime"));  //发布时间
			String signalName = String.valueOf(paramMap.get("disasterId"));  //信号名称
			String warninglevel = String.valueOf(paramMap.get("warninglevel_Id"));  //预警级别

			//LocalDateTime代替calandar
			LocalDateTime now = LocalDateTime.parse(publishDate);
			now.minusHours(8);

			String targetPath = newPathFile+"/"+sourceFile.getName();
	     	foi.copyFile(rootPath+"uploadfile/alarmPlan/warningreport.FZQ", targetPath);
			File file=new File(targetPath);
			DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

			File newfile=new File(newPathFile+"/"+"WP"+ now.format(sf)+fileend);
			file.renameTo(newfile);
			targetPath=newPathFile+"/"+"WP"+ now.format(sf)+fileend;

			String signal = "";
			if("11012".equals(signalName)){
				signal+="A";

			}else if("11014".equals(signalName)){
				signal+="B";

			}else if("11020".equals(signalName)){
				signal+="C";

			}else if("11045".equals(signalName)){
				signal+="D";

			}else if("11028".equals(signalName)){
				signal+="E";

			}else if("11030".equals(signalName)){
				signal+="F";
			//雷雨大风
			}else if("11031".equals(signalName)){
				signal+="G";
			//雷暴
			}else if("11025".equals(signalName)){
				signal+="S";
			//森林火险
			}else if("11036".equals(signalName)){
				signal+="J";

			}
			//解除
			if("10".equals(warninglevel)){
				signal+="0";
			//红色
			}else if("1".equals(warninglevel)){
				signal+="1";
			//橙色
			}else if("2".equals(warninglevel)){
				signal+="2";
			//黄色
			}else if("3".equals(warninglevel)){
				signal+="3";
			//蓝色
			}else if("4".equals(warninglevel)){
				signal+="4";
			//白色
			}else if("9".equals(warninglevel)){
				signal+="9";

			}
			signal+="000";
			FileWriter writer =new FileWriter(targetPath,true);
			StringBuffer stf = new StringBuffer();
			stf.append("ZCZC");
			stf.append("\n");
			stf.append("YJXH90"+"  "+code+"  "+now.format(DateTimeFormatter.ofPattern("ddHHmm")));
			stf.append("\n");
			stf.append(now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"))+"  "+code+"  "+signal+"=");
			stf.append("\n");
			stf.append("NNNN");
			writer.write(stf.toString());
			writer.close();//关闭输出流

			Map<String,Object> resultMaps = new HashMap<String, Object>();
			resultMaps.put("reportpath", "uploadfile/officefile/report/" + timeString+"/"+newfile.getName());
			return resultMaps;
		}
}
