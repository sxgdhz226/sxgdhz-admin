package com.ruoyi.project.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigsUtils {

	private static ConfigsUtils configs;
	
	private String prodDownPath;
	private String prodTempName;
	private String prodTempPath;
	private String phoonListInterface;
	private String phoonWay;
	private String phoonDetail;
	private String weatherSeven;
	private String future24hour;
	private String weatherdesc;
	private String ldtFolderPath;
	private String stationId1;
	private String stationId2;
	private String stationId3;
	private String longProdImage;
	private String wxYtFolderPath;
	private String WxYtgFolderPath;
	private String shengldtFplderPath01;
	private String shengldtFplderPath02;
	private String regionldtFplderPath;
    
	
	
	private ConfigsUtils() {

	}

	static Properties prop = null;
	static {
		prop = new Properties();
		try {
			File file = ResourceUtils.getFile("classpath:properties/configs.properties");
			InputStream in = new FileInputStream(file);
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ConfigsUtils getConfigs() {
		if (configs == null) {
			configs = new ConfigsUtils();
			configs.setPhoonListInterface(prop.getProperty("phoonListInterface").trim());
			configs.setProdTempName(prop.getProperty("prodTempName").trim());
			configs.setLongProdImage(prop.getProperty("longProdImage").trim());
			configs.setProdDownPath(prop.getProperty("prodDownPath").trim());
			configs.setPhoonWay(prop.getProperty("phoonWayInterface").trim());
			configs.setProdTempPath(prop.getProperty("prodTempPath").trim());
			configs.setWeatherSeven(prop.getProperty("weatherSeven").trim());
			configs.setLdtFolderPath(prop.getProperty("ldtFolderPath").trim());
			configs.setFuture24hour(prop.getProperty("future24hour").trim());
			configs.setPhoonDetail(prop.getProperty("phoonDetail").trim());
			configs.setStationId1(prop.getProperty("stationId1").trim());
			configs.setStationId2(prop.getProperty("stationId2").trim());
			configs.setStationId3(prop.getProperty("stationId3").trim());
			configs.setWxYtFolderPath(prop.getProperty("wxYtFolderPath").trim());
			configs.setWxYtgFolderPath(prop.getProperty("WxYtgFolderPath").trim());
			configs.setShengldtFplderPath01(prop.getProperty("shengldtFplderPath01").trim());
			configs.setShengldtFplderPath02(prop.getProperty("shengldtFplderPath02").trim());
			configs.setRegionldtFplderPath(prop.getProperty("regionldtFplderPath").trim());
		}
		return configs;
	}
	
	
	public String getStationId1() {
		return stationId1;
	}
	public void setStationId1(String stationId1) {
		this.stationId1 = stationId1;
	}
	public String getStationId2() {
		return stationId2;
	}
	public void setStationId2(String stationId2) {
		this.stationId2 = stationId2;
	}
	public String getLdtFolderPath() {
		return ldtFolderPath;
	}
	public void setLdtFolderPath(String ldtFolderPath) {
		this.ldtFolderPath = ldtFolderPath;
	}
	public String getLongProdImage() {
		return longProdImage;
	}
	public void setLongProdImage(String longProdImage) {
		this.longProdImage = longProdImage;
	}
	public String getProdTempName() {
		return prodTempName;
	}

	public void setProdTempName(String prodTempName) {
		this.prodTempName = prodTempName;
	}

	public String getProdDownPath() {
		return prodDownPath;
	}

	public void setProdDownPath(String prodDownPath) {
		this.prodDownPath = prodDownPath;
	}

	public String getProdTempPath() {
		return prodTempPath;
	}

	public void setProdTempPath(String prodTempPath) {
		this.prodTempPath = prodTempPath;
	}

	public String getPhoonWay() {
		return phoonWay;
	}

	public void setPhoonWay(String phoonWay) {
		this.phoonWay = phoonWay;
	}

	public String getPhoonListInterface() {
		return phoonListInterface;
	}

	public void setPhoonListInterface(String phoonListInterface) {
		this.phoonListInterface = phoonListInterface;
	}

	public String getWeatherSeven() {
		return weatherSeven;
	}

	public void setWeatherSeven(String weatherSeven) {
		this.weatherSeven = weatherSeven;
	}

	public String getFuture24hour() {
		return future24hour;
	}

	public void setFuture24hour(String future24hour) {
		this.future24hour = future24hour;
	}

	public String getWeatherdesc() {
		return weatherdesc;
	}

	public void setWeatherdesc(String weatherdesc) {
		this.weatherdesc = weatherdesc;
	}

	public String getPhoonDetail() {
		return phoonDetail;
	}

	public void setPhoonDetail(String phoonDetail) {
		this.phoonDetail = phoonDetail;
	}

	public String getStationId3() {
		return stationId3;
	}

	public void setStationId3(String stationId3) {
		this.stationId3 = stationId3;
	}

	public String getWxYtFolderPath() {
		return wxYtFolderPath;
	}

	public void setWxYtFolderPath(String wxYtFolderPath) {
		this.wxYtFolderPath = wxYtFolderPath;
	}

	public String getWxYtgFolderPath() {
		return WxYtgFolderPath;
	}

	public void setWxYtgFolderPath(String wxYtgFolderPath) {
		WxYtgFolderPath = wxYtgFolderPath;
	}


	public String getShengldtFplderPath01() {
		return shengldtFplderPath01;
	}


	public void setShengldtFplderPath01(String shengldtFplderPath01) {
		this.shengldtFplderPath01 = shengldtFplderPath01;
	}


	public String getShengldtFplderPath02() {
		return shengldtFplderPath02;
	}


	public void setShengldtFplderPath02(String shengldtFplderPath02) {
		this.shengldtFplderPath02 = shengldtFplderPath02;
	}


	public String getRegionldtFplderPath() {
		return regionldtFplderPath;
	}


	public void setRegionldtFplderPath(String regionldtFplderPath) {
		this.regionldtFplderPath = regionldtFplderPath;
	}



	
}