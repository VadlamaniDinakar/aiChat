package com.ai.aiApi.aiController;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ai.aiApi.service.AIOrchestrator;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AIController {

	@Autowired
	private AIOrchestrator orchestrator;

	private List<Map<String, Object>> conversation = new ArrayList<>();

	@PostMapping("/chat")
	public String chat(@RequestBody String prompt) {
		log.info("In the method chat(). Processing postmapping /chat.");
		// add user message
		String systemPrompt = """
			    You are a helpful assistant.
			    IMPORTANT formatting rules:
			    - Always wrap terminal/shell commands in ```bash blocks
			    - Always wrap Java code in ```java blocks
			    - Always wrap Python code in ```python blocks
			    - Never return plain commands without code blocks
			    """;
		conversation.add(Map.of("role", "system", "content", systemPrompt));
		conversation.add(Map.of("role", "user", "content", prompt));
		
		

		String response = orchestrator.getResponse(conversation);

		// add AI response
		conversation.add(Map.of("role", "assistant", "content", response));

		log.info("Exiting method chat(). Response is:{}");

		return response;
	}
}