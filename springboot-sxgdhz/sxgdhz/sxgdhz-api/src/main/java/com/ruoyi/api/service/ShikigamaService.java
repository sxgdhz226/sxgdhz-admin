package com.ruoyi.api.service;


import com.ruoyi.api.entity.ShikigamiModel;

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
