package com.ruoyi.service.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ruoyi.api.service.ITyphoonService;
import com.ruoyi.api.entity.Typhoon;
import com.ruoyi.service.mapper.TyphoonInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typhoonServiceImpl")
public class TyphoonServiceImpl implements ITyphoonService{

    @Autowired
    private TyphoonInfoMapper typhoonInfoMapper;

    @Override
    public List<Typhoon> queryTyphoonForList() {
        return typhoonInfoMapper.selectList(new EntityWrapper<Typhoon>().eq("TSCNAME", "山竹"));
        //return typhoonInfoMapper.queryTyphoonForList();
    }

    @Override
    public List<Typhoon> queryTyphoonForPageList(Page page) {

        EntityWrapper<Typhoon> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new Typhoon());
        //entityWrapper.where("flag = {0}","0");
        entityWrapper.orderBy("createtime");

        System.out.println("SqlSegment: "+entityWrapper.getSqlSegment());

        typhoonInfoMapper.selectPage(page, entityWrapper).forEach(e -> {
            System.out.println(e.getTscname());
        });
        return typhoonInfoMapper.selectPage(page, entityWrapper);

    }
}
