package com.ruoyi.service.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ruoyi.api.entity.Typhoon;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * sxx
 * 2015-07-25
 */

public interface TyphoonInfoMapper extends BaseMapper<Typhoon>{


    List<Typhoon> selectMyPage(RowBounds rowBounds, @Param("ew") Wrapper<Typhoon> wrapper);


    public List<Typhoon> queryTyphoonForList();
}
