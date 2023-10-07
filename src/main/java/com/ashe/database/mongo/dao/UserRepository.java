package com.ashe.database.mongo.dao;

import com.ashe.database.mongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * 获取集合下所有文档的数量
     */
    long count();


    Optional<User> findByName(String name);
}
