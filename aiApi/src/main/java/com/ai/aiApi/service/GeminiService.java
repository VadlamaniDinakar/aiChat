package com.ai.aiApi.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ai.aiApi.apiProviderInterface.AiProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GeminiService implements AiProvider {

	private final WebClient webClient;

	@Value("${gemini.api.key}")
	private String apiKey;

	@Value("${gemini.url}")
	private String url;

	public GeminiService(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public String generateResponse(List<Map<String, Object>> messages) {
		log.info("Entering into method generateResponse() of GeminiService.");

		List<Object> contents = new ArrayList<>();

		for (Map<String, Object> msg : messages) {
			contents.add(Map.of("parts", new Object[] { Map.of("text", msg.get("content")) }));
		}

		Map<String, Object> requestBody = Map.of("contents", contents);

		Map response = webClient.post().uri(url + "?key=" + apiKey).header("Content-Type", "application/json")
				.bodyValue(requestBody).retrieve().bodyToMono(Map.class).block();

		var candidates = (List<Map>) response.get("candidates");
		var content = (Map) candidates.get(0).get("content");
		var parts = (List<Map>) content.get("parts");
		String responseResult = parts.get(0).get("text").toString();
		log.info("Exiting from the method generateResponse() of GeminiService.Response is " + responseResult);

		return responseResult;
	}
}