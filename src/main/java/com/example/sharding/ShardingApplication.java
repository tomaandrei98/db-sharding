package com.example.sharding;

import com.example.sharding.entity.UserEntity;
import com.example.sharding.service.UserShardingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShardingApplication {

	private final UserShardingService service;

	public ShardingApplication(UserShardingService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(ShardingApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return (run) -> {
			service.saveUser("alice");
			service.saveUser("bob");
		};
	}
}
