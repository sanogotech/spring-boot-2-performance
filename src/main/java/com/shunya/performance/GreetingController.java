package com.shunya.performance;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;

@RestController
public class GreetingController {
	
	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	private static final String template = "Hello, %s!";
	private static final String templateMNPF = "OK,Merci, Moov, Transaction numero %s!";
	private final AtomicLong counter = new AtomicLong();
	
	private final GitHubLookupService gitHubLookupService;

	public GreetingController (GitHubLookupService gitHubLookupService) {
		this.gitHubLookupService = gitHubLookupService;
	}

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	
	@GetMapping("/oLDhelloasync")
	public Greeting OLDhelloasync(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception{
		logger.info("Start helloasync");
		CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
		logger.info("En Cours, Update Legacy Database ...");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("/helloasync")
	public Greeting helloasync(@RequestParam(value = "montant", defaultValue = "15000") String montant) throws Exception{
		logger.info("****************** Start Payer avec API MNPF *********************");
		CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
		logger.info("Je viens de Stocker dans le cache /session/ Redis/ File Attente  ...");
		logger.info("En Cours, Update Legacy Database ... Patience");
		return new Greeting(counter.incrementAndGet(), String.format(templateMNPF, montant));
	}
	
	/*
	 public Mono<ServerResponse> ping(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\n" +
                        "  \"status\": \"ok\"\n" +
                        "}");
    }*/
}
