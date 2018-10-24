package com.ruoyi.framework.config.crawler.service;

import com.ruoyi.framework.config.crawler.model.ShikigamiModel;

import java.util.List;

public interface ShikigamaService {

    /**
     * 根据名字查式神
     *
     * @param name
     * @return
     */
    ShikigamiModel findByName(String name);

    /**
     * 保存
     *
     * @param model
     */
    void save(ShikigamiModel model);

    /**
     * 查所有
     *
     * @return
     */
    List<ShikigamiModel> findAll();
}
