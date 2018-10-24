package com.ruoyi.project.web.config.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * sxx
 * 2015-07-25
 */

public interface TyphoonInfoMapper {

    /****从省局接口查询台风列表********/
    public List<Map<String,Object>> getTyphoonName(String year);

    /****根据台风id查询台风********/
    public long getTyphoonByTsid(@Param("tsid") int tsid);

    public int insertTyphoon(@Param("insertTyphoonSql") String insertTyphoonSql);

    /***********插入台风实况*******************/
    public int insertTyphoonActual(@Param("insertTyphoonActualSql") String insertTyphoonActualSql);

    /***********插入台风预报*******************/
    public int insertTyphoonForeCast(@Param("insertTyphoonForeCastSql") String insertTyphoonForeCastSql);

    public int insertTyphoonList(@Param("list") List<Map<String, Object>> typhoonList);

    /***********插入台风实况*******************/
    public int insertTyphoonActualList(@Param("list") List<Map<String, Object>> typhoonActualList);

    /***********插入台风预报*******************/
    public int insertTyphoonForeCastList(@Param("list") List<Map<String, Object>> typhoonForeCastList);

    public int isExistTyphoonActual(Map<String, Object> map);

    public int isExistTyphoonMetar(Map<String, Object> map);

    public int insertTyphoonActualMap(Map<String, Object> map);

    public int insertTyphoonForeCastMap(Map<String, Object> map);

    /********************根据台风编号删除台风信息*****************************/
    public int deleteTyphoonActualByTcid(@Param("tsid") int tsid);

    public int deleteTyphoonMetarByTcid(@Param("tsid") int tsid);

    public List<Map<String,Object>> searchTyphoonByNum(String year);

    public List<Map<String,Object>> getTyphoonSkPathByNameId(String nameId);

    public List<Map<String,Object>> getTyphoonYbPathByItemId(String item_id);

}
