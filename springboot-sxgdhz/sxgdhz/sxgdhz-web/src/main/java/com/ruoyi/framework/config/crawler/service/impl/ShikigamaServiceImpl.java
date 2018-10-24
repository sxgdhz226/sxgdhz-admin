package com.ruoyi.framework.config.crawler.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.framework.config.crawler.dao.ShikigamaDao;
import com.ruoyi.framework.config.crawler.model.ShikigamiModel;
import com.ruoyi.framework.config.crawler.service.ShikigamaService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShikigamaServiceImpl implements ShikigamaService {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ShikigamaServiceImpl.class);

    @Autowired
    private ShikigamaDao dao;

    @Override
    public ShikigamiModel findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public void save(ShikigamiModel model) {
        ShikigamiModel shikigamiModel = dao.findByName(model.getName());
        if (shikigamiModel == null) {
            dao.save(model);
            LOGGER.debug("插入数据:{}", JSON.toJSONString(model));
        }
    }

    @Override
    public List<ShikigamiModel> findAll() {
        return dao.findAll();
    }
}
