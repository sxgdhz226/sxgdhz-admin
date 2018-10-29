package com.ruoyi.api.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.ruoyi.api.entity.Typhoon;

import java.util.List;

public interface ITyphoonService {

    public List<Typhoon> queryTyphoonForList();

    public List<Typhoon> queryTyphoonForPageList(Page page);
}
