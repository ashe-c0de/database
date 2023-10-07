package com.ashe.database.mongo.service;

import com.alibaba.fastjson2.JSON;
import com.ashe.database.infra.ServiceException;
import com.ashe.database.mongo.dao.UserRepository;
import com.ashe.database.mongo.domain.User;
import com.ashe.database.mongo.domain.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void insert(UserDTO dto) {
        User user = new User();
        long count = userRepository.count();
        user.setId(String.format("00%s", count + 1));
        user.setName(dto.getName());
        user.setQuote(dto.getQuote());
        user.setCountry(dto.getCountry());
        userRepository.insert(user);
    }


    public void update(UserDTO dto) {
        boolean exists = userRepository.existsById(dto.getId());
        if (exists) {
            User user = new User();
            user.setId(dto.getId());
            user.setName(dto.getName());
            user.setQuote(dto.getQuote());
            user.setCountry(dto.getCountry());
            userRepository.save(user);
        }

    }

    public void remove(String id) {
        Optional<User> byId = userRepository.findById(id);
        byId.ifPresent(userRepository::delete);
    }
}
