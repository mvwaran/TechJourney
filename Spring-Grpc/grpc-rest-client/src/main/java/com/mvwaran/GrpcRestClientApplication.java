package com.mvwaran;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GrpcRestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcRestClientApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl("http://localhost:8081")
				.build();
	}

	@Bean
	public ManagedChannel managedChannel() {
		return ManagedChannelBuilder.forAddress("localhost", 9091)
				.usePlaintext()
				.build();
	}

}
