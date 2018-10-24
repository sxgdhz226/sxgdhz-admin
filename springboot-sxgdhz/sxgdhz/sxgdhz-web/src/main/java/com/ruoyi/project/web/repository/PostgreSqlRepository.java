package com.ruoyi.project.web.repository;

import com.ruoyi.project.web.entity.PostgreUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgreSqlRepository extends JpaRepository<PostgreUser, Long> {
}
