package com.ruoyi.service.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.api.entity.ShikigamiModel;
import com.ruoyi.api.service.ShikigamaService;
import com.ruoyi.service.user.dao.ShikigamaDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Service 注意是导入dubbo的注解
 */
@Service("shikigamaServiceImpl")
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
