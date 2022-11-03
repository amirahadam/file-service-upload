package com.cognixia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration(exclude={org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration.class})
public class FileServiceUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileServiceUploadApplication.class, args);
	}

}
