package com.ai.aiApi.apiProviderInterface;

import java.util.*;

public interface AiProvider {

	String generateResponse(List<Map<String, Object>> messages);

}
