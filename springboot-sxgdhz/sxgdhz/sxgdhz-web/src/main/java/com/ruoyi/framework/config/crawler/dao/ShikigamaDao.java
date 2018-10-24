package com.ruoyi.framework.config.crawler.dao;

import com.ruoyi.framework.config.crawler.model.ShikigamiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShikigamaDao extends JpaRepository<ShikigamiModel, Long> {

    /**
     * 根据名字查式神
     *
     * @param name
     * @return
     */
    ShikigamiModel findByName(String name);
}
