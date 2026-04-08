# 🤖 Multi-AI Chat Application (Spring Boot)

A robust **Spring Boot-based AI chat application** that integrates with multiple AI providers and ensures high availability using a **fallback mechanism**.

---

## 🚀 Overview

This application allows users to interact with AI through a simple web interface (`index.html`).
It intelligently connects to multiple AI providers in sequence:

1. **Anthropic API** (Primary)
2. **OpenAI API** (Fallback 1)
3. **Gemini API** (Fallback 2)

If one provider fails, the system automatically switches to the next available provider.

---

## ✨ Features

* 🔁 **Multi-AI fallback support**
* 🌐 Simple web-based chat UI
* ⚡ Fast and lightweight Spring Boot backend
* 🔐 API key-based configuration
* 🧠 Smart AI response handling
* 💻 No frontend framework required (Plain HTML + JS)

---

## 🏗️ Architecture

```
User (Browser)
     ↓
index.html (UI)
     ↓
Spring Boot Controller (/ai/chat)
     ↓
AI Service Layer
     ↓
Anthropic → OpenAI → Gemini (Fallback Chain)
```

---

## 📦 Prerequisites

* Java 17+
* Maven
* API Keys for:

  * Anthropic
  * OpenAI
  * Gemini

---

## ⚙️ Configuration

Update your `application.properties`:

```properties
# Anthropic
anthropic.api.key=YOUR_ANTHROPIC_API_KEY

# OpenAI
openai.api.key=YOUR_OPENAI_API_KEY

# Gemini
gemini.api.key=YOUR_GEMINI_API_KEY
```

---

## ▶️ Running the Application

### Using Maven

```bash
mvn clean install
mvn spring-boot:run
```


---

## 🌐 Access the Application

Once the application starts:

```
http://localhost:8080/index.html
```

---

## 💬 API Endpoint

### Chat Endpoint

```
POST /ai/chat
```

### Request Body

```json
"Your prompt as a message"
```

### Response

```
AI generated response (plain text / formatted)
```

---

## 🔁 Fallback Logic

The application follows this sequence:

1. Try **Anthropic**
2. If failed → Try **OpenAI**
3. If failed → Try **Gemini**
4. If all fail → Return error response

---

## 📁 Project Structure

```
src/
 ├── controller/
 ├── service/
 │    ├── AnthropicService
 │    ├── OpenAIService
 │    └── GeminiService
 ├── config/
 └── resources/
      ├── application.properties
      └── static/index.html
```

---

## 🛠️ Customization

You can:

* Modify fallback order
* Add more AI providers
* Enhance UI in `index.html`
* Add streaming responses

---

## ⚠️ Error Handling

* Gracefully handles API failures
* Automatically switches providers
* Returns user-friendly messages

---

## 🔐 Security Notes

* Never expose API keys in frontend
* Keep `application.properties` secure
* Use environment variables for production

---

## 🚀 Future Enhancements

* Streaming responses (like ChatGPT)
* Chat history persistence
* Authentication & user sessions
* File upload + AI analysis
* Voice input support

---

## 👨‍💻 Author

**Dinakar**

---

## ⭐ Support

If you like this project, consider giving it a ⭐ on GitHub!
