package com.ai.aiApi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ai.aiApi.apiProviderInterface.AiProvider;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Service
@Slf4j
public class AnthropicService implements AiProvider {

	private final WebClient webClient;

	@Value("${anthropic.api.key}")
	private String apiKey;

	@Value("${anthropic.url}")
	private String url;

	public AnthropicService(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public String generateResponse(List<Map<String, Object>> messages) {
		log.info("Entering into method generateResponse() of AnthropicService.");
		// Convert messages → Anthropic format
		List<Object> formattedMessages = new ArrayList<>();

		for (Map<String, Object> msg : messages) {
			formattedMessages.add(Map.of("role", msg.get("role"), "content",
					new Object[] { Map.of("type", "text", "text", msg.get("content")) }));
		}

		Map<String, Object> requestBody = Map.of("model", "claude-3-sonnet-20240229", "max_tokens", 1000, "messages",
				formattedMessages);

		Map response = webClient.post().uri(url).header("x-api-key", apiKey).header("anthropic-version", "2023-06-01")
				.header("Content-Type", "application/json").bodyValue(requestBody).retrieve().bodyToMono(Map.class)
				.block();

		var content = (java.util.List<Map>) response.get("content");
		String responseResult = content.get(0).get("text").toString();
		log.info("Exiting from the method generateResponse() of AnthropicService.Response is " + responseResult);
		return responseResult;
	}
}