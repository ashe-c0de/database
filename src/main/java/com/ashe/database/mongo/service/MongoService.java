package com.ashe.database.mongo.service;

import com.alibaba.fastjson2.JSON;
import com.ashe.database.infra.ServiceException;
import com.ashe.database.mongo.dao.UserRepository;
import com.ashe.database.mongo.domain.User;
import org.springframework.stereotype.Service;

@Service
public class MongoService {

    private final UserRepository userRepository;

    public MongoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getOne() {
        User user = userRepository.findByName("Ashe")
                .orElseThrow(() -> new ServiceException("Ashe is not exist!"));
        return JSON.toJSONString(user);
    }

}
