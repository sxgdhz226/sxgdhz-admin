package com.ruoyi.project.web.controller;

import com.ruoyi.common.mapper.JsonMapper;
import com.ruoyi.project.utils.*;
import com.ruoyi.project.web.service.TyphoonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    private TyphoonService typhoonService;

    @RequiresPermissions("web:typhoon:view")
    @RequestMapping(value = "typhoon")
    public String tflj(Model model,String nameId,String name,HttpServletRequest request) throws FileNotFoundException {
        Map<String,Object>	cachexmlmap=CacheXMLMap.getCacheXmlMap();
        String con=cachexmlmap.get("typhoonName")==null?"":cachexmlmap.get("typhoonName").toString();
        String webPath = request.getSession().getServletContext().getRealPath("/");
        if(null==con||"".equals(con)){
            File file = ResourceUtils.getFile("classpath:static/share/typhoon/typhoonName.json");
            con = WriteFileUtil.getContentByFile(file);
            cachexmlmap.put("typhoonName", con);
        }

        @SuppressWarnings("unchecked")
        List<Map<String,Object>> nameList = JsonMapper.josnToObject(con, List.class);
        List<Map<String,Object>> nameListParam=new ArrayList<Map<String,Object>>();
        for(int i=0;i<nameList.size();i++){
            Map<String,Object> param=nameList.get(i);
            String sid=param.get("sid").toString();
//			if(sid.contains("热带低压")){
//				continue;
//			}
            nameListParam.add(param);
        }
        model.addAttribute("nameList", nameListParam);
        model.addAttribute("defalutId", nameId);
        model.addAttribute("menu", "tflj");
        getTyphoonDateInfo(model,webPath);
        return "typhoon/tflj";
    }

    private void getTyphoonDateInfo(Model model,String webPath) throws FileNotFoundException {
        /**判断台风是否展示,初始台风最后一点数据*/
        Map<String,Object>	cachexmlmap=CacheXMLMap.getCacheXmlMap();
        String lastTyphoonCon= cachexmlmap.get("lastTyphoon")==null?"":cachexmlmap.get("lastTyphoon").toString();
        if(null ==lastTyphoonCon || "".equals(lastTyphoonCon)||"[]".equals(lastTyphoonCon)){

            File file = ResourceUtils.getFile("classpath:static/share"+File.separatorChar+"typhoon"+File.separatorChar+"lastTyphoon.json");
            lastTyphoonCon = WriteFileUtil.getContentByFile(file);
            cachexmlmap.put("lastTyphoon", lastTyphoonCon);
        }
        model.addAttribute("typhoonState", "false");
        if(null==lastTyphoonCon ||"".equals(lastTyphoonCon)||"[]".equals(lastTyphoonCon)){
            return;
        }
        String picurl = PathProperties.jeecmsPath("savePicpath");
        File filepic = new File(picurl);
        String picname[] = filepic.list();
        String name = "";
        if(picname!=null && picname.length!=0){
            Arrays.sort(picname);
            name = picname[picname.length-1];
            model.addAttribute("typhoon", name);
        }
        List<Map<String, String>> lastTyphInfoList = JsonMapper.josnToObject(lastTyphoonCon, ArrayList.class);
        if(lastTyphInfoList!=null && !lastTyphInfoList.isEmpty()){
            String nameIds="";
            String names="";
            String tycodes="";
            Map<String,Object> param=ownMap();
            String enalbeIds=param.get("enableIds")==null?"":param.get("enableIds").toString();
            String defau=param.get("default")==null?"":param.get("default").toString();//默认第一个显示
            String defId="";
            String tycode="";
            String firstName="";
            String firstCode="";
            boolean boolflag=true;
            if(!"".equals(defau)){//存在默认台风
                defId=defau;//默认数据
                boolflag=false;
            }

            for(int i=0;i<lastTyphInfoList.size();i++){
                Map<String,String> lastTyphInfo=lastTyphInfoList.get(i);
                String nameId = lastTyphInfo.get("nameId");
                if(enalbeIds.contains(nameId)){//禁止的显示的台风
                    continue;
                }
                if(lastTyphInfo.get("state").equals("1")){//显示
                    model.addAttribute("typhoonState", "show");
                }
                String name1=lastTyphInfo.get("name");
                String code1=lastTyphInfo.get("code");
                if(defau.equals(nameId)){//自定义默认数字
                    tycode=code1;
                    firstName=name1;
                    firstCode=code1;
                    defId=nameId;
                    continue;
                }
                if(boolflag){//系统默认
                    tycode=code1;
                    firstName=name1;
                    firstCode=code1;
                    defId=nameId;
                    boolflag=false;
                    continue;
                }
                nameIds+=nameId+",";
                names+=name1+",";
                tycodes+=code1+",";
            }
            if(!"".equals(defId)){
                nameIds=nameIds+defId+",";
                names=names+firstName+",";
                tycodes=tycodes+firstCode+",";
            }

            if(!"".equals(nameIds) && boolflag){//获取最小的id号[默认台风]
                String allname=nameIds.substring(0, nameIds.length()-1);
                String[] allNames=allname.split(",");
                defId=allNames[0];//默认 为最新的那个
                String allcode=tycodes.substring(0, tycodes.length()-1);
                String[] allcodes=allcode.split(",");
                tycode=allcodes[0];
            }
            if(!"".equals(defId)){
                String lastTyphoonInfoCon=(String) cachexmlmap.get(defId+"_sk");
                if(null==lastTyphoonInfoCon||"".equals(lastTyphoonInfoCon)){
                    String lastTyphoonInfo = webPath +"share"+File.separatorChar+"typhoon"+File.separatorChar+""+defId+"_sk.json";
                    lastTyphoonInfoCon = WriteFileUtil.getContent(lastTyphoonInfo);
                    cachexmlmap.put(defId+"_sk",lastTyphoonInfoCon);
                }

                List<Map<String,String>> lastTyphSkInfo = JsonMapper.josnToObject(lastTyphoonInfoCon, ArrayList.class);
                if(lastTyphSkInfo!=null&&!lastTyphSkInfo.isEmpty()){
                    int leng = lastTyphSkInfo.size()-1;
                    Map<String,String> info = lastTyphSkInfo.get(leng);
                    String windspeed = info.get("windspeed");
                    if(windspeed!=null && windspeed.trim().length()!=0){
                        double wind = Double.parseDouble(windspeed);
                        String grade ="";
                        if (wind > 10.8 && wind <= 17.1) {
                            grade = "热带低压";
                        } else if (wind > 17.2 && wind <= 24.4) {
                            grade = "热带风暴";
                        } else if (wind > 24.5 && wind <= 32.6) {
                            grade = "强热带风暴";
                        } else if (wind > 32.7 && wind <= 41.4) {
                            grade = "台风";
                        } else if (wind > 41.5 && wind <= 50.9) {
                            grade = "强台风";
                        } else if (wind > 51.0) {
                            grade = "超强台风";
                        }
                        info.put("grade", grade);
                    }
                    model.addAttribute("lastTyphSkInfo", info);
                }
            }

            if(!"".equals(nameIds)){
                nameIds = nameIds.substring(0,nameIds.length()-1);
                names=names.substring(0, names.length()-1);
                tycodes=tycodes.substring(0, tycodes.length()-1);
            }
            model.addAttribute("tycode", tycode);
            model.addAttribute("typhoonId", nameIds);
            model.addAttribute("typhoonName", names);
            model.addAttribute("typhoonCode", tycodes);
        }
        /**台风end*/
    }


    /**
     * 对外提供省局接口台风路径
     * 供gis使用
     */
    @RequestMapping(value = "tfljIntersk")
    public void tfljIntersk(Model model, String nameId, String name, HttpServletResponse response, HttpServletRequest request, String callback) {

        String path = request.getSession().getServletContext().getRealPath(File.separatorChar+"share"+File.separatorChar+"typhoon");
        try {
            if(nameId!=null && !nameId.equals("")){
                Map<String,Object> cachexmlmap= CacheXMLMap.getCacheXmlMap();
                String con=(String) cachexmlmap.get(nameId+"_sk");
                if(null==con||"".equals(con)){
                    path =path+ File.separatorChar+nameId+"_sk.json";
                    con = WriteFileUtil.getContent(path);
                    cachexmlmap.put(nameId+"_sk", con);
                }

                if(con==null || con.trim().length()==0){//没有该台风，查询数据库数据。
                    if(nameId.length()==6){
                        nameId = nameId.substring(2);
                    }
                    name = URLDecoder.decode(name,"UTF-8");
                    con = typhoonService.searchTyphoonByNum(nameId,name);
                }
                List<Map<String,Object>> nameList = JsonMapper.josnToObject(con, List.class);
                if(nameList !=null && nameList.size() >0){
                    for(int i=0;i<nameList.size();i++){
                        String tyname=nameList.get(i).get("name").toString();
                        tyname=tyname.replace(" ", "");
                        nameList.get(i).put("name", tyname);
                    }
                }
                con= JsonMapper.toJsonString(nameList);
                con= callback + "(" + con + ")";
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setCharacterEncoding("utf-8");

                response.getWriter().print(con);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对外提供省局接口台风路径
     * 供gis使用
     */
    @RequestMapping(value = "tfljInteryb")
    public void tfljInteryb(Model model,String nameId,String name,String lastTime,HttpServletResponse response,HttpServletRequest request,String callback) {
        try {
            if(!StringUtils.isBlank(nameId)){
                SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmss");
                if(lastTime==null || lastTime.length()==0){
                    lastTime=sm.format(new Date());
                }else{
                    SimpleDateFormat sm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        lastTime=sm.format(sm1.parse(lastTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(nameId!=null && !nameId.equals("")){
                    String path = request.getSession().getServletContext().getRealPath(File.separatorChar+"share"+File.separatorChar+"typhoon");
                    Map<String,Object>	cachexmlmap=CacheXMLMap.getCacheXmlMap();
                    String con=(String) cachexmlmap.get(nameId+"_yb");
                    if(null==con||"".equals(con)){
                        path =path+File.separatorChar+nameId+"_yb.json";
                        con = WriteFileUtil.getContent(path);
                        cachexmlmap.put(nameId+"_yb", con);
                    }

                    if(null==con || "".equals(con) ||!con.startsWith("[")){
                        response.setHeader("Access-Control-Allow-Origin", "*");
                        response.setCharacterEncoding("utf-8");
                        String outcon= callback + "([])";
                        response.getWriter().print(outcon);
                    }else{
                        JSONArray arrays= JSONArray.fromObject(con);
                        if(arrays.size()>0){
                            JSONObject object=(JSONObject) arrays.get(0);
                            String data=object.getString("data");
                            List<Map<String,Object>> nameList = JsonMapper.josnToObject(data, List.class);
                            if(nameList !=null && nameList.size() >0){
                                for(int i=0;i<nameList.size();i++){
                                    String tyname=nameList.get(i).get("name").toString();
                                    tyname=tyname.replace(" ", "");
                                    nameList.get(i).put("name", tyname);
                                }
                            }
                            con=JsonMapper.toJsonString(nameList);
                            con= callback + "(" + con + ")";
                            response.setHeader("Access-Control-Allow-Origin", "*");
                            response.setCharacterEncoding("utf-8");

                            response.getWriter().print(con);
                        }else{
                            response.setHeader("Access-Control-Allow-Origin", "*");
                            response.setCharacterEncoding("utf-8");
                            con= callback + "([])";
                            response.getWriter().print(con);
                        }
                    }
                }
            }else{
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setCharacterEncoding("utf-8");
                String con= callback + "([])";
                response.getWriter().print(con);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @RequestMapping(value="tfljcallback")
    public void tfljcallback(Model model,String nameId,String name,HttpServletRequest request,HttpServletResponse response,String callback){
        Map<String,Object>	cachexmlmap=CacheXMLMap.getCacheXmlMap();
        String con=cachexmlmap.get("typhoonName")==null?"":cachexmlmap.get("typhoonName").toString();
        if(null==con||"".equals(con)){
            String path = request.getSession().getServletContext().getRealPath("/share/typhoon/typhoonName.json");
            con = WriteFileUtil.getContent(path);
            cachexmlmap.put("typhoonName", con);
        }
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> nameList = JsonMapper.josnToObject(con, List.class);
        List<Map<String,Object>> nameListParam=new ArrayList<Map<String,Object>>();
        for(int i=0;i<nameList.size();i++){
            Map<String,Object> param=nameList.get(i);
            String sid=param.get("sid").toString();
            String enName=param.get("name").toString();
            enName=enName.replace(" ", "");
//			if(sid.contains("热带低压")){
//				continue;
//			}
            param.put("name", enName);
            nameListParam.add(param);
        }
        con=JsonMapper.toJsonString(nameListParam);
        con=con.replace("sid", "nameId");
        con=con.replace("enName", "Ename");
        try {
            con= callback + "(" + con + ")";
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setCharacterEncoding("utf-8");

            response.getWriter().print(con);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @RequestMapping(value="typhoonindex")
    public void typhoonindex(Model model,HttpServletRequest request,String callback,HttpServletResponse response){
        Map<String,Object>	cachexmlmap=CacheXMLMap.getCacheXmlMap();
        String lastTyphoonCon=cachexmlmap.get("lastTyphoon")==null?"":cachexmlmap.get("lastTyphoon").toString();
        if(null==lastTyphoonCon || "".equals(lastTyphoonCon)){
            String webPath = request.getSession().getServletContext().getRealPath("/");
            String lastTyphoon = webPath +"share"+File.separatorChar+"typhoon"+File.separatorChar+"lastTyphoon.json";
            lastTyphoonCon = WriteFileUtil.getContent(lastTyphoon);
            cachexmlmap.put("lastTyphoon", lastTyphoonCon);
        }
        List<Map<String,Object>> myparam=new ArrayList<Map<String,Object>>();
        List<Map<String, String>> lastTyphInfoList = JsonMapper.josnToObject(lastTyphoonCon, ArrayList.class);
        if(lastTyphInfoList!=null && !lastTyphInfoList.isEmpty()){
            String nameIds="";
            String names="";
            String tycodes="";
            Map<String,Object> param= ownMap();
            String enalbeIds=param.get("enableIds")==null?"":param.get("enableIds").toString();
            String defau=param.get("default")==null?"":param.get("default").toString();//默认第一个显示
            String defId="";
            String tycode="";
            String firstName="";
            String firstCode="";
            boolean boolflag=true;
            if(!"".equals(defau)){//存在默认台风
                defId=defau;//默认数据
                boolflag=false;
            }
            for(int i=0;i<lastTyphInfoList.size();i++){
                Map<String,String> lastTyphInfo=lastTyphInfoList.get(i);
                String nameId = lastTyphInfo.get("nameId");
                if(enalbeIds.contains(nameId)){//禁止的显示的台风
                    continue;
                }
                String name1=lastTyphInfo.get("name");
                String code1=lastTyphInfo.get("code");
                if(defau.equals(nameId)){//自定义默认数字
                    tycode=code1;
                    firstName=name1;
                    firstCode=code1;
                    defId=nameId;
                    continue;
                }
                if(boolflag){//系统默认
                    tycode=code1;
                    firstName=name1;
                    firstCode=code1;
                    defId=nameId;
                    boolflag=false;
                    continue;
                }
                nameIds+=nameId+",";
                names+=name1+",";
                tycodes+=code1+",";
            }
            if(!"".equals(defId)){
                nameIds=defId+","+nameIds;
                names=firstName+","+names;
                tycodes=firstCode+","+tycodes;
            }

            if(!"".equals(nameIds) && boolflag){//获取最小的id号[默认台风]
                String allname=nameIds.substring(0, nameIds.length()-1);
                String[] allNames=allname.split(",");
                defId=allNames[0];//默认 为最新的那个
                String allcode=tycodes.substring(0, tycodes.length()-1);
                String[] allcodes=allcode.split(",");
                tycode=allcodes[0];
            }


            if(!"".equals(nameIds)){
                nameIds = nameIds.substring(0,nameIds.length()-1);
                names=names.substring(0, names.length()-1);
                tycodes=tycodes.substring(0, tycodes.length()-1);
            }
            String[] arrays=nameIds.split(",");
            String[] arrayNames=names.split(",");
            String[] arrayCodes=tycodes.split(",");
            for(int i=0;i<arrays.length;i++){
                Map<String,Object> myownmap=new HashMap<String, Object>();
                myownmap.put("id", arrays[i]);
                myownmap.put("name", arrayNames[i]);
                myownmap.put("nameId", arrayCodes[i]);
                myownmap.put("Ename", "");
                myparam.add(myownmap);
            }
        }
        try {
            String con=JsonMapper.toJsonString(myparam);
            con= callback + "(" + con + ")";
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(con);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private Map<String,Object> ownMap(){
        Map<String,Object> map=new HashMap<String, Object>();
        Map<String,Object>	cachexmlmap=CacheXMLMap.getCacheXmlMap();
        String content=cachexmlmap.get("typhoonEnable")==null?"":cachexmlmap.get("typhoonEnable").toString();
        if(null==content||"".equals(content)){
            String path= PathProperties.jeecmsPath("sharePath");
            path =path+"typhoonEnable.xml";
            content=WriteFileUtil.getContent(path);
        }
        Dom4jUtil dom4j=new Dom4jUtil();
        String nameIds=dom4j.splitDesc(content, "nameIds");
        String defau=dom4j.splitDesc(content, "default");
        map.put("enableIds", nameIds);
        map.put("default", defau);
        return map;
    }
}
