package com.ruoyi.project.web.service;

import com.ruoyi.common.mapper.JsonMapper;
import com.ruoyi.project.utils.HttpClienUtil;
import com.ruoyi.project.web.config.mapper.TyphoonInfoMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 实况逻辑层
 * @author yzq
 *
 */
@Service
@Transactional(readOnly = true)
public class TyphoonService {

	@Autowired
	private TyphoonInfoMapper typhoonDao;
	
	private SimpleDateFormat sm  =new SimpleDateFormat("yy");
	
	
	/**
	 * 获取台风名称列表
	 * 数据库数据
	 */
	@Deprecated
	public List<Map<String,Object>> getTyphoonName(){
		return typhoonDao.getTyphoonName(sm.format(new Date()));
	}
	
	/**
	 * 根据年份id查询台风实况信息。
	 * 数据库数据
	 */
	public String searchTyphoonByNum(String year, String name){
		List<Map<String,Object>> list = typhoonDao.searchTyphoonByNum(year);
		List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
		int count = list.size()-1;
		SimpleDateFormat sm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i =count;i>=0;i--){
			Map<String,Object> map = list.get(i);
			
			Map<String,Object> tem = new HashMap<String, Object>();
			tem.put("longitude", map.get("LONGITUDE"));
			tem.put("latitude", map.get("LATITUDE"));
			tem.put("time", sm1.format(map.get("DATETIME")));
			tem.put("windspeed", map.get("MAXV"));
			tem.put("windforce", converWindSpeed(map.get("MAXV")+""));
			tem.put("rr07", "");
			tem.put("passthrough", "0");//当前时间点
			tem.put("name","");//当前时间点
			tem.put("gust","");
			tem.put("pressure", "");
			tem.put("name", name);
			tem.put("ret", "true");
			res.add(tem);
		}
		String con ="";
		con = JsonMapper.toJsonString(res);
		return con;
	}
	
	/**
	 * 根据台风名字查询实况路径信息
	 * 数据库数据
	 * @throws ParseException
	 */
	@Deprecated
	public List<Map<String,Object>> getTyphoonPathByNameId(String nameId, String name){
		List<Map<String,Object>> temp1 = typhoonDao.getTyphoonSkPathByNameId(nameId);
		List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
		int i =1;
		String lastId="";
		String lastTime = "";
		for(Map<String,Object> map : temp1){
			if(i==temp1.size()){
				lastId = map.get("ID").toString();
				lastTime = map.get("DATETIME").toString();
			}
			map.put("passthrough", "0");//当前时间点
			map.put("name",name);//当前时间点
			res.add(map);
			i++;
		}
		temp1 = typhoonDao.getTyphoonYbPathByItemId(lastId);
		try {
			for(Map<String,Object> map : temp1){
				map.put("passthrough", "1");//当前时间点
				map.put("name",name);//当前时间点
				String valid = map.get("VALIDTIME").toString();
				if(valid!=null){
					int v = Integer.parseInt(valid);
					long times = 1000*60*60*v;
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					long skTime;
					skTime = sm.parse(lastTime).getTime();
					long ybTime = times+skTime;
					String tt = sm.format(new Date(ybTime));
					map.put("DATETIME", tt);
					res.add(map);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 省局接口
	 * 获取台风名字接口信息
	 * 只获取最近两年台风
	 */
	public List<Map<String,Object>> getTyphoonNamesByWeb(){
		SimpleDateFormat sm = new SimpleDateFormat("yy");
		String year = sm.format(new Date());
		String lasYear = Integer.parseInt(year)-1 +"";
		String breakYear = Integer.parseInt(year)-2 +"";
		String url = "http://172.22.1.175/di/http.action?userId=gmcrzh&pwd=zhuh123&interfaceId=getRACTyphoonInfo&dataFormat=json";
		String jsonDate = HttpClienUtil.sendGet(url, "",null);
		@SuppressWarnings("unchecked")
        Map<String, List<Map<String, Object>>> maps = JsonMapper.josnToObject(jsonDate, Map.class);
		if(maps == null || maps.get("DATA")==null){
			return null;
		}
		List<Map<String, Object>> list = maps.get("DATA");
		List<Map<String, Object>> typhoonList = new ArrayList<Map<String,Object>>();//最近两年
		for(Map<String, Object> map : list){
			String time = map.get("CRTTIME").toString();
			if(time.indexOf("20"+breakYear)!=-1){//倒叙，一旦出现第三年数据，跳出循环。超不粗20**年
				break;
			}
			typhoonList.add(map);
		}
		
		List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> resdesc = new ArrayList<Map<String,Object>>();//倒叙存放
		int leng = typhoonList.size()-1;
		int nowYearNum=0;//今年未命名台风编号
		int lastYearNum =0;//去年未命名台风编号
		for (int j = leng; j >=0; j--) {
			Map<String,Object> map = typhoonList.get(j);
			String name  = map.get("TSCNAME").toString();
			if(name==null||name.length()==0){
				name="未命名";
			}
			String listId = map.get("TSID").toString();
			String crtTime = map.get("CRTTIME").toString();
			String sid = map.get("INTLID").toString();
			if(sid==null || sid.equals("") || sid.equals("****")){
				sid="热带低压";
				if(crtTime.indexOf("20"+year)!=-1){
					nowYearNum++;
					if(nowYearNum<10){
						sid+=year+"0"+nowYearNum;
					}else{
						sid+=year+nowYearNum;
					}
						
				}else if(crtTime.indexOf("20"+lasYear)!=-1){
					lastYearNum++;
					if(lastYearNum<10){
						sid+=lasYear+"0"+lastYearNum;
					}else{
						sid+=lasYear+lastYearNum;
					}
				}
			}
			Map<String, Object> tem = new HashMap<String, Object>();
			tem.put("id", listId);
			tem.put("name", name);
			tem.put("sid", sid);
			tem.put("enName", map.get("TSENAME").toString());
			res.add(tem);
		}
		
		if(res.size()>0){
			int resLeng = res.size()-1;
			for(int k=resLeng;k>=0;k--){
				resdesc.add(res.get(k));
			}
		}
		
		return resdesc;
	}
	
	/**
	 * 省局接口
	 * 根据台风id 获取台风实况路径
	 */
	public List<Map<String,Object>> getTyphoonNamesByWebsk(String nameId, String name, String orgid){
		String url = "http://172.22.1.175/di/http.action?userId=gmcrzh&pwd=zhuh123&interfaceId=getRACTyphoonObs4Tsid&dataFormat=json&tsid="+nameId+"&fcid="+orgid;
		String jsonDate = HttpClienUtil.sendGet(url, "",null);
		@SuppressWarnings("unchecked")
        Map<String, List<Map<String, Object>>> maps = JsonMapper.josnToObject(jsonDate, Map.class);
		List<Map<String, Object>> list = maps.get("DATA");
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sm1 = new SimpleDateFormat("yyyy-MM-dd HH");
		List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
		int count = list.size()-1;
		for(int i =count;i>=0;i--){
			Map<String,Object> map = list.get(i);
			Map<String,Object> tem = new HashMap<String, Object>();
			tem.put("longitude", map.get("LONGITUDE"));
			tem.put("latitude", map.get("LATITUDE"));
			String datatime = map.get("DDATETIME").toString();
			Date dd = new Date();
			try {
				dd = sm.parse(datatime);
				datatime = sm1.format(addHoureDate(dd, +8));
			} catch (ParseException e) {
			}
			
			tem.put("time", datatime+":00:00");
			tem.put("windspeed", map.get("WINDSPEED"));
			tem.put("windforce", converWindSpeed(map.get("WINDSPEED")+""));
			String fq = map.get("RR07")+"";
			if(fq=="")
				fq="0";
			tem.put("rr07", fq);
			tem.put("passthrough", "0");//当前时间点
			tem.put("name",name);//当前时间点
			tem.put("gust", map.get("GUST"));
			tem.put("pressure", map.get("PRESSURE"));
			tem.put("distances", "0");
			double longt1= Double.parseDouble(tem.get("longitude")+"");
			double lat1= Double.parseDouble(tem.get("latitude")+"");
			int d = getDistance(longt1, lat1);
			tem.put("distances", d);
			res.add(tem);
		}
		return res;
	}
	
	/**
	 * 省局接口
	 * 根据台风id 获取台风不同机构预报路径
	 * @param lastTime 开始预报时间
	 * @param name		名字
	 * @param nameId	id
	 * @param fcid	预报机构
	 * @return
	 */
	public List<Map<String,Object>> getTyphoonNamesByWebyb(String lastTime, String nameId, String name, String fcid, List<Map<String, Object>> skList){
		List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sm3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//页面展示使用
		String te = getLastYbTime(fcid, nameId,sm3);//最新更新时间
		try {
			Map<String, List<Map<String, Object>>> mapsyb = null;
			String url ="http://172.22.1.175/di/http.action?userId=gmcrzh&pwd=zhuh123&interfaceId=getRACTyphoonFst4Tsid&dataFormat=json&tsid="+nameId+"&fcid="+fcid+"&ymdhms="+te;
			String jsonDateyb = HttpClienUtil.sendGet(url, "",null);
			mapsyb = JsonMapper.josnToObject(jsonDateyb, Map.class);
			List<Map<String, Object>> listyb = mapsyb.get("DATA");
			if(listyb!=null && listyb.size()!=0){
				Map<String,Object> firstYb = null;
				String time = listyb.get(0).get("DDATETIME").toString();
				Date dd = sm3.parse(time);
				time = sm3.format(addHoureDate(dd, +8));
				for(Map<String,Object> map :skList){
					String timesk = map.get("time").toString();
					if(time.equals(timesk)){
						firstYb = map;
						break;
					}
				}
				if(firstYb==null && skList!=null &&skList.size()!=0){
					firstYb = skList.get(0);
				}
				double longt1= Double.parseDouble(firstYb.get("longitude")+"");
				double lat1= Double.parseDouble(firstYb.get("latitude")+"");
				int d = getDistance(longt1, lat1);
				firstYb.put("distances", d);
				res.add(firstYb);
			}
			
			for(Map<String,Object> map : listyb){
				Map<String,Object> tem = new HashMap<String, Object>();
				tem.put("longitude", map.get("LONGITUDE"));//经纬
				tem.put("latitude", map.get("LATITUDE"));//纬度
				tem.put("windspeed", map.get("WINDSPEED"));//风速
				tem.put("windforce", converWindSpeed(map.get("WINDSPEED")+""));//风力
				String fq = map.get("RR07")+"";
				if(fq=="")
					fq="0";
				tem.put("rr07", fq);//7级风圈半径
				tem.put("passthrough", "1");//预报时间起始点
				tem.put("name",name);
				String valid = map.get("LEADTIME").toString();
				tem.put("gust", map.get("GUST"));
				tem.put("LEADTIME", map.get("LEADTIME"));
				tem.put("pressure", map.get("PRESSURE"));
				if(valid!=null){
					String dd = map.get("DDATETIME").toString();
					Date d =sm3.parse(dd);
					
					int h =8+ Integer.parseInt(valid);
					String tt = sm3.format(addHoureDate(d, h));
					tem.put("time", tt);//拼接24，48时间转换
					double longt1= Double.parseDouble(tem.get("longitude")+"");
					double lat1= Double.parseDouble(tem.get("latitude")+"");
					int dd1 = getDistance(longt1, lat1);
					tem.put("distances", dd1);
					
					res.add(tem);
				}
			}
		
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 查询历史影响珠海台风
	 */
//	public List<TyphoonLog> searchTyphoonBad(){
//
//		List<TyphoonLog> result = typhoonDao.searchTyphoonBad();
//		return result;
//	}
	
	/**
	 * 风速转风力
	 * @param speed
	 * @return
	 */
	private String converWindSpeed(String speed){
		if(speed!=null && speed.length()!=0 && !speed.equals("null")){
			double sd = Double.parseDouble(speed);
			if(sd>61.2){
				return "17";
			}else if(sd>=56.1){
				return "17";
			}else if(sd>=51.0){
				return "16";
			}else if(sd>=46.2){
				return "15";
			}else if(sd>=41.5){
				return "14";
			}else if(sd>=37.0){
				return "13";
			}else if(sd>=32.7){
				return "12";
			}else if(sd>=28.5){
				return "11";
			}else if(sd>=24.5){
				return "10";
			}else if(sd>=20.8){
				return "9";
			}else if(sd>=17.2){
				return "8";
			}else if(sd>=13.9){
				return "7";
			}else if(sd>=10.8){
				return "6";
			}else if(sd>=8.0){
				return "5";
			}else if(sd>=5.5){
				return "4";
			}else if(sd>=3.4){
				return "3";
			}else if(sd>=1.6){
				return "2";
			}else if(sd>=0.3){
				return "1";
			}else{
				return "0";
			}
		}
		return "0";
	}
	
	
	/**
	 * 获取最新预报更新数据
	 * @return
	 */
	public String getLastYbTime(String org, String typhoonId, SimpleDateFormat sm1){
		String url = "http://172.22.1.175/di/http.action?interfaceId=getRACTyphoonFstTimeRange4Tsid&userId=gmcrzh&Pwd=zhuh123&dataFormat=json&tsid="+typhoonId+"&fcid="+org;
		String json = HttpClienUtil.sendGet(url, "", "UTF-8");
		JSONObject obj = JSONObject.fromObject(json);
		List<Map<String,String>> res = (List<Map<String, String>>) obj.get("DATA");
		if(res!=null && res.size()!=0){
			String time = res.get(0).get("DDATETIME");
			try {
				time = sm1.format(sm1.parse(time));
			} catch (ParseException e) {
			}
			
			time = time.replace("-", "");
			time = time.replace(".", "");
			time = time.replace(":", "");
			time = time.replace(" ", "");
			return time;//最新预报时间。格式：yyyyMMddHHmmss;
		}
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
		return sm.format(new Date())+"000000";
	}
	
	/*
	 * 坐标点与珠海的距离
	 */
	public int getDistance(double lng1,double lat1){
		try{
			double lng2=113.56;
			double lat2=22.27;
			
	        double radLat1 = Math.toRadians(lat1);
	        double radLat2 = Math.toRadians(lat2);
	        double a = radLat1 - radLat2;
	        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
	                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
	        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
	        s = Math.round(s * 10000) / 10000;
	        int d =(int)s/1000;
			int j =d%10;
			d = d-j;
			if(j>=5){
				d+=10;
			}
	        return d;
		}catch (Exception e){
			return 0;
		}
    }

	/**
	 * 对date进行小时加减，
	 * 主要用于世界时间和北京时间转换
	 * @param dd 时间
	 * @param num 加减小时
	 * @return
	 */
	public Date addHoureDate(Date dd, int num){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dd);
		cal.add(Calendar.HOUR_OF_DAY, num);
		return cal.getTime();
	}
	
}
