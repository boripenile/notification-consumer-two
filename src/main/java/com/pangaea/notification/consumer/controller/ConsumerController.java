package com.pangaea.notification.consumer.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pangaea.notification.consumer.events.ConsumerEventManager;

@RestController
public class ConsumerController {

	private final ConsumerEventManager consumerEvent;

	public ConsumerController(ConsumerEventManager consumerEvent) {
		this.consumerEvent = consumerEvent;
	}
	
	@GetMapping(value = "/test2", produces = "application/json")
	public ResponseEntity<String> test2(HttpServletRequest request) {
		String urlPath = ServletUriComponentsBuilder.fromRequestUri(request)
				.build().toUriString();
		JSONObject response = consumerEvent.readCurrentMessage(urlPath);
		if (response != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(response.toString());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new JSONObject().put("message", "No message available").toString());
	}
	
}
