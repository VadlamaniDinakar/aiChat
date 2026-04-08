package com.ai.aiApi.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AIOrchestrator {

	@Autowired
	private AnthropicService anthropicService;

	@Autowired
	private OpenAIService openAIService;

	@Autowired
	private GeminiService geminiService;

	public String getResponse(List<Map<String, Object>> messages) {

		try {
			log.info("In the method getResponse(). Processing anthropicService to generate response.");
			return anthropicService.generateResponse(messages);

		} catch (Exception e1) {

			log.error("In the method getResponse(). Processing anthropicService failed.{}", e1.getMessage());

			try {
				log.info("In the method getResponse(). Processing openAIService to generate response.");

				return openAIService.generateResponse(messages);

			} catch (Exception e2) {

				log.error("In the method getResponse(). Processing openAIService failed. {}", e2.getMessage());

				log.info("In the method getResponse(). Processing geminiService to generate response.");

				return geminiService.generateResponse(messages);
			}
		}
	}
}