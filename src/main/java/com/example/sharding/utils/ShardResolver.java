package com.example.sharding.utils;

import org.springframework.stereotype.Component;


@Component
public class ShardResolver {
    private static final int SHARD_COUNT = 2;

    public int getShardNumber(String key) {
        return Math.floorMod(key.hashCode(), SHARD_COUNT);
    }
}
