package com.ruoyi.project.utils;

import java.util.HashMap;
import java.util.Map;

public class CacheXMLMap {

    public static Map<String,Object> cacheXmlMap=new HashMap<String, Object>();

    private static Map<String,Object> cacheStationMap=new HashMap<String, Object>();

    private static Map<String,Object> cacheVideoMap=new HashMap<String, Object>();

    public static Map<String, Object> getCacheXmlMap() {
        return cacheXmlMap;
    }

    public static void setCacheXmlMap(Map<String, Object> cacheXmlMap) {
        CacheXMLMap.cacheXmlMap = cacheXmlMap;
    }

    public static Map<String, Object> getCacheStationMap() {
        return cacheStationMap;
    }

    public static void setCacheStationMap(Map<String, Object> cacheStationMap) {
        CacheXMLMap.cacheStationMap = cacheStationMap;
    }

    public static Map<String, Object> getCacheVideoMap() {
        return cacheVideoMap;
    }

    public static void setCacheVideoMap(Map<String, Object> cacheVideoMap) {
        CacheXMLMap.cacheVideoMap = cacheVideoMap;
    }
}
