package com.ruoyi.service.user.dao;

import com.ruoyi.api.entity.ShikigamiModel;
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
