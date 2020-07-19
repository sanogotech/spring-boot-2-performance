package com.shunya.performance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

@Service
public class GitHubLookupService {

	private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);


	@Autowired
	private RestTemplate restTemplate;

	/*
	@Autowired
	public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}*/

	@Async
	public CompletableFuture<User> findUser(String user) throws InterruptedException {
		logger.info("Start  Async Task ..");
		//logger.info("Looking up " + user);
		String url = String.format("https://api.github.com/users/%s", user);
		User results = restTemplate.getForObject(url, User.class);
		// Artificial delay of 1s for demonstration purposes
		Thread.sleep(50000L);
		logger.info("End Async Task");
		logger.info("End Update Database ...");
		return CompletableFuture.completedFuture(results);
	}

}
