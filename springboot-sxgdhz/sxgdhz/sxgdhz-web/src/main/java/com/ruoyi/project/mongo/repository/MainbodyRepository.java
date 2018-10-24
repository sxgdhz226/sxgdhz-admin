package com.ruoyi.project.mongo.repository;

import com.ruoyi.project.mongo.entity.Mainbody;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author
 */
public interface MainbodyRepository extends MongoRepository<Mainbody, String> {

	Mainbody findByCode(String defaultMainbodyCode);

}
