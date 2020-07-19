package com.shunya.performance;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	
	private final GitHubLookupService gitHubLookupService;

	public  HelloController  (GitHubLookupService gitHubLookupService) {
		this.gitHubLookupService = gitHubLookupService;
	}
	
	private final AtomicLong counter = new AtomicLong();
	
	private static final String templateMNPF = "OK,Merci, Moov, Transaction numero %s!";

    public Mono<ServerResponse> ping(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\n" +
                        "  \"status\": \"ok\"\n" +
                        "}");
    }
    
    

	public Mono<ServerResponse> myasync(ServerRequest serverRequest) {
		logger.info("****************** Start Payer avec API MNPF *********************");
		try {
			CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Je viens de Stocker dans le cache /session/ Redis/ File Attente  ...");
		logger.info("En Cours, Update Legacy Database ... Patience");
		
		Greeting greeting =new Greeting(counter.incrementAndGet(), String.format(templateMNPF, 15000));
		
		ResponseEntity<Greeting> responseGreeting = new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
		return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(responseGreeting);
	}

}
