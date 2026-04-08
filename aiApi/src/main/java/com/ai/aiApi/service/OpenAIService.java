package com.ai.aiApi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.ai.aiApi.apiProviderInterface.AiProvider;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OpenAIService implements AiProvider {

	private final WebClient webClient;

	@Value("${openai.api.key}")
	private String apiKey;

	@Value("${openai.url}")
	private String url;

	public OpenAIService(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public String generateResponse(List<Map<String, Object>> messages) {
		log.info("Entering into method generateResponse() of OpenAIService.");

		Map<String, Object> requestBody = Map.of("model", "gpt-4.1-mini", "messages", messages);

		Map response = webClient.post().uri(url).header("Authorization", "Bearer " + apiKey)
				.header("Content-Type", "application/json").bodyValue(requestBody).retrieve().bodyToMono(Map.class)
				.block();

		var choices = (java.util.List<Map>) response.get("choices");
		var message = (Map) choices.get(0).get("message");

		String responseResult = message.get("content").toString();
		log.info("Exiting from the method generateResponse() of OpenAIService.Response is " + responseResult);

		return responseResult;
	}
}