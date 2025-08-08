package com.example.sharding.repository.shard1;

import com.example.sharding.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserShard1Repository extends JpaRepository<UserEntity, Long> {
}
