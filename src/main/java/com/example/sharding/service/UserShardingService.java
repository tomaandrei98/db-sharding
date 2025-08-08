package com.example.sharding.service;

import com.example.sharding.entity.UserEntity;

public interface UserShardingService {
    void saveUser(String username);

    UserEntity fetchUser(String username);
}
