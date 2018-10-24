package com.ruoyi.project.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件对接，xml解析类
 * @author yzq
 *
 */
public class Dom4jUtil {
	
	private SAXReader reader = new SAXReader();
	
	/**
	 * 不重复xml，转map
	 * @param path 路径
	 * @return
	 */
	public Map<String,Map<String, String>> getXmlValueToMap(String path){
		Map<String,Map<String, String>> result = new HashMap<String, Map<String, String>>();
		try {
			path = path.replaceAll("%20", " ");
			File file = new File(path);
			if(file.length()==0){
				return null;
			}
			Document document = reader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			Element obj = null;
			Element tmp = null;
			while (iterator.hasNext()) {
				obj = (Element) iterator.next();
				if (obj != null) {
					Map<String,String> map = new HashMap<String, String>();
					Iterator<Element> temp = obj.elementIterator();
					if(temp.hasNext()){
						while (temp.hasNext()) {
							tmp = (Element) temp.next();
							if (tmp != null) {
								map.put(tmp.getName(), tmp.getText());
							}
						}
					}else{
						map.put(obj.getName(), obj.getText());
					}
					if(!map.isEmpty()){
						result.put(obj.getName(), map);
					}
				}
			}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}  
		return result;
	}
	
	/**
	 * 不重复xml，三层嵌套转map
	 * @param path 路径
	 * @return
	 */
	public Map<String,Map<String, Map<String, String>>> getXmlValueToThreeMap(String path){
		File fileExist=new File(path);
		if(fileExist.exists()&& fileExist.length()==0l){
			return new HashMap<String, Map<String,Map<String,String>>>();
		}
		Map<String,Map<String, Map<String, String>>> result = new HashMap<String, Map<String,  Map<String, String>>>();
		try {
			path = path.replaceAll("%20", " ");
			File file = new File(path);
			if(file.length()==0){
				return null;
			}
			Document document = reader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			Element obj = null;
			Element tmp = null;
			Element tmp1 = null;
			while (iterator.hasNext()) {//根节点
				obj = (Element) iterator.next();
				if (obj != null) {
					Map<String,Map<String, String>> map = new HashMap<String, Map<String, String>>();
					Iterator<Element> temp = obj.elementIterator();
					while (temp.hasNext()) {//二级节点
						tmp = (Element) temp.next();
						if (tmp != null) {
							Iterator<Element> temp1 = tmp.elementIterator();
							Map<String, String> threeMap = new HashMap<String, String>();
							
							if(!temp1.hasNext()){
								Map<String, String> t =  new HashMap<String, String>();
								t.put(tmp.getName(), tmp.getText());
								map.put(tmp.getName(),t);
							}else{
								map.put(tmp.getName(), threeMap);
							}
							while (temp1.hasNext()) {//三级节点
								tmp1 = (Element) temp1.next();
								if (tmp1 != null) {
									threeMap.put(tmp1.getName(), tmp1.getText());
								}
							}
						}
					}
					if(!map.isEmpty()){
						result.put(obj.getName(), map);
					}
				}
			}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}  
		return result;
	}
	/**
	 * 不重复xml，转element节点下map
	 * @param path 路径
	 * @param element 节点
	 * @return
	 */
	public Map<String,Map<String, String>> getXmlValueToMap(String path, String element){
		Map<String,Map<String, String>> result = new HashMap<String, Map<String, String>>();
		try {
			path = path.replaceAll("%20", " ");
			File file = new File(path);
			if(file.length()==0){
				return null;
			}
			Document document = reader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			Element obj = null;
			Element tmp = null;
			while (iterator.hasNext()) {
				obj = (Element) iterator.next();
				if (obj != null) {
					if(element!=null && element.length()>0){
						if(obj.getName().equals(element)){
							Map<String,String> map = new HashMap<String, String>();
							Iterator<Element> temp = obj.elementIterator();
							while (temp.hasNext()) {
								tmp = (Element) temp.next();
								if (tmp != null) {
									map.put(tmp.getName(), tmp.getText());
								}
							}
							if(!map.isEmpty()){
								result.put(obj.getName(), map);
							}
							
						}
					}else{
						Map<String,String> map = new HashMap<String, String>();
						Iterator<Element> temp = obj.elementIterator();
						while (temp.hasNext()) {
							tmp = (Element) temp.next();
							if (tmp != null) {
								map.put(tmp.getName(), tmp.getText());
							}
						}
						if(!map.isEmpty()){
							result.put(obj.getName(), map);
						}
					}
				}
			}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}  
		return result;
	}
	/**
	 * 重复节点，转list
	 * @param path 路径
	 * @param name 节点
	 * @return
	 */
	public List<Map<String, String>> getXmlValueToList(String path){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		try {
			path = path.replaceAll("%20", " ");
			File file = new File(path);
			if(file.length()==0){
				return null;
			}
			Document document = reader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			Element obj = null;
			Element tmp = null;
			while (iterator.hasNext()) {
				obj = (Element) iterator.next();
				if (obj != null) {
					Map<String,String> map = new HashMap<String, String>();
					Iterator<Element> temp = obj.elementIterator();
					while (temp.hasNext()) {
						tmp = (Element) temp.next();
						if (tmp != null) {
							map.put(tmp.getName(), tmp.getText());
						}
					}
					if(!map.isEmpty()){
						result.add(map);
					}
				}
			}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}  
		return result;
	}
	/**
	 * 
	 * @param path 路径
	 * @param name 节点
	 * @return
	 */
	public String getXmlValueByName(String path, String name){
		try {
			path = path.replaceAll("%20", " ");
			File file = new File(path);
			if(file.length()==0){
				return null;
			}
			Document document = reader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			Element obj = null;
			Element tmp = null;
			while (iterator.hasNext()) {
				obj = (Element) iterator.next();
				if (obj != null) {
					Iterator<Element> temp = obj.elementIterator();
					while (temp.hasNext()) {
						tmp = (Element) temp.next();
						if (tmp != null) {
							if(tmp.getName().equals(name)){
								return tmp.getText();
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}  
		return null;
	}
	
	/**
	 * 
	 * @param path 路径
	 * @param name 节点
	 * @param arrt 属性
	 * @return
	 */
	public String getXmlAttrByName(String path, String name, String arrt){
		try {
			path = path.replaceAll("%20", " ");
			File file = new File(path);
			if(file.length()==0){
				return null;
			}
			Document document = reader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			Element obj = null;
			Element tmp = null;
			while (iterator.hasNext()) {
				obj = (Element) iterator.next();
				if (obj != null) {
					Iterator<Element> temp = obj.elementIterator();
					while (temp.hasNext()) {
						tmp = (Element) temp.next();
						if (tmp != null) {
							if(tmp.getName().equals(name)){
								return tmp.attribute(arrt).getValue();
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	
	/**
	 * 
	 * 任意节点遍历，转map。
	 * 二层使用
	 * @return
	 */
	public Map<String,Map<String, String>> getXmlValueToMaps(String path, String element){
		Map<String,Map<String, String>> result = new HashMap<String, Map<String, String>>();
		try {
			path = path.replaceAll("%20", " ");
			File file = new File(path);
			if(file.length()==0){
				return null;
			}
			Document document = reader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			Element obj = null;
			Element tmp = null;
			while (iterator.hasNext()) {
				obj = (Element) iterator.next();
				if (obj != null) {
					setMapVal(obj, element, result);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
		return result;
	}
	
	
	public void setMapVal(Element obj, String element, Map<String,Map<String, String>> result){
		Iterator<Element> tem1 = obj.elementIterator();
		while (tem1.hasNext()) {
			obj = (Element) tem1.next();
			if(obj.getName().equals(element)){
				Map<String,String> map = new HashMap<String, String>();
				Iterator<Element> temp = obj.elementIterator();
				while (temp.hasNext()) {
					Element tmp = (Element) temp.next();
					if (tmp != null) {
						map.put(tmp.getName(), tmp.getText());
					}
				}
				if(!map.isEmpty()){
					result.put(obj.getName(), map);
				}
			}else{
				setMapVal(obj, element, result);
			}
		}
	}
	/**
	  * 正则截取指定节点内容
	  * @param ele
	  * @return
	  */
	public String splitDesc(String con , String els){
		 Pattern p = Pattern.compile("<"+els+">(.*?)</"+els+">");
		 Matcher m = p.matcher(con);
		 boolean find = m.find();
		 if(find==true){
	    		return m.group(1);
	    }
		 return null;
	 }
}
