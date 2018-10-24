package com.ruoyi.project.mongo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.sql.DataSourceDefinition;

/**
 * 使用lombok使代码变得简洁，编译成class后会自动加上set/get
 * @NonNull : 让你不在担忧并且爱上NullPointerException
 * @CleanUp : 自动资源管理：不用再在finally中添加资源的close方法
 *
 *  注：如果不指定collection则默认为MongoUser首字母小写
 *  Indexed 声明该字段需要索引，建索引可以大大的提高查询效率。
 *  @DBRef - 声明类似于关系数据库的关联关系。
 *  @Transient映射忽略的字段
 *
 *
 */
//@Document(collection = "user")
@Data
@ToString
public class MongoUser {

    @DBRef
    private Mainbody mainbody;
    @Id
    private Integer id;
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String name;
    private Integer age;
    @Transient
    private String phone;

//    @PersistenceConstructor
//    public MongoUser(Mainbody mainbody, Integer id, String name, Integer age) {
//        this.mainbody = mainbody;
//        this.id = id;
//        this.name = name;
//        this.age = age;
//    }
}
