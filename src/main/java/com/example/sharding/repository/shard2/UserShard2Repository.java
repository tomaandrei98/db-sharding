package com.example.sharding.repository.shard2;

import com.example.sharding.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserShard2Repository extends JpaRepository<UserEntity, Long> {
}
