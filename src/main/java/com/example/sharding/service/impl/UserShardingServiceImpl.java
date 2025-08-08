package com.example.sharding.service.impl;

import com.example.sharding.entity.UserEntity;
import com.example.sharding.repository.UserRepository;
import com.example.sharding.repository.shard1.UserShard1Repository;
import com.example.sharding.repository.shard2.UserShard2Repository;
import com.example.sharding.service.UserShardingService;
import com.example.sharding.utils.ShardResolver;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserShardingServiceImpl implements UserShardingService {
    private final UserShard1Repository shard1Repository;
    private final UserShard2Repository shard2Repository;
    private final ShardResolver shardResolver;

    public UserShardingServiceImpl(UserShard1Repository shard1Repository, UserShard2Repository shard2Repository, ShardResolver shardResolver) {
        this.shard1Repository = shard1Repository;
        this.shard2Repository = shard2Repository;
        this.shardResolver = shardResolver;
    }

    @Override
    public void saveUser(String username) {
        getRepositoryFor(username).save(new UserEntity(username));
    }

    public UserEntity fetchUser(String username) {
        Optional<UserEntity> user = getRepositoryFor(username).findByUsername(username);
        return user.orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    private UserRepository getRepositoryFor(String username) {
        int shardNumber = shardResolver.getShardNumber(username);
        if (shardNumber == 0) {
            return shard1Repository;
        } else {
            return shard2Repository;
        }
    }
}
