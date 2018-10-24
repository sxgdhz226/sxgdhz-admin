package com.ruoyi.project.mongo.repository;

import com.ruoyi.project.mongo.entity.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * 'name':1   1代表为true的意思
 * Integer  指定id的类型
 */
public interface UserRepository extends MongoRepository<MongoUser,Integer> {

    /**
     * 非传入参数查询  不等于
     * @return
     */
    @Query(value = "{'name':{'$ne':null}}",fields = "{'name':1}")
    public List<MongoUser> queryByNameAndPage();

    /**
     * 传入参数查询 ?0是占位符
     */
    @Query(value = "{'name': ?0}",fields = "{'name':1}")
    public List<MongoUser> queryByNameAndPage(String name);

    /**
     * 传入参数查询 ?0是占位符  大于
     */
    @Query(value = "{'age': {'$gt':0}}",fields = "{'name':1}")
    public List<MongoUser> findByAgeGreaterThan();

    /**
     * 两者之间 between
     */
    @Query(value = "{'age': {'$gt': 0,'$lt': 999}}",fields = "{'name':1}")
    public List<MongoUser> findByAgeGreaterThan(int from,int to);
}
