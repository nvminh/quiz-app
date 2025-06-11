
# 🧠 Real-Time Vocabulary Quiz System

A microservices-based quiz platform using **Spring Boot**, **Kafka**, and **Docker**. It supports quiz creation, real-time score tracking, user participation, and leaderboard updates using Kafka.

---

## 📌 Features

- Create and manage quizzes with questions, start/end times
- Join quizzes and track participation
- Submit answers, calculate score, and publish via Kafka
- Kafka consumer updates leaderboard in real-time
- RESTful APIs tested via Postman collection

---

## 📂 Source Code

Clone the repo:

```bash
git clone https://github.com/nvminh/quiz-app.git
cd quiz-app
````

---

## 🛠 Requirements

* Java 17+
* Docker (for Kafka setup)
* Gradle (or use `./gradlew` wrapper)

---

## ⚙️ Kafka Setup

### Option 1: Script-based install

```bash
chmod +x kafka/install-kafka.sh
./kafka/install-kafka.sh
```

### Option 2: Manual Docker setup

```bash
# Create network
docker network create kafka-net

# Start Zookeeper
docker run -d --name zookeeper --network kafka-net -p 2181:2181 \
  -e ZOOKEEPER_CLIENT_PORT=2181 \
  confluentinc/cp-zookeeper:7.5.0

# Start Kafka
docker run -d --name kafka --network kafka-net -p 9092:9092 \
  -e KAFKA_BROKER_ID=1 \
  -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  confluentinc/cp-kafka:7.5.0
```

---

## 🚀 Running Services

From the project root:

### 🎯 Quiz Service (port 8080)

```bash
./gradlew :quiz-service:bootRun
```

### 🏆 Leaderboard Service (port 8081)

```bash
./gradlew :leaderboard-service:bootRun
```

---

## 📬 REST API Usage (Sample)

You can import the Postman collection [collection.json](https://github.com/nvminh/quiz-app/blob/main/docs/Quiz%20Service%20APIs.postman_collection.json)

> **URL**: `http://localhost:8080`
> **Alternative**: Use `curl` or `httpie`

---

### ✅ Create Quiz

```http
POST /api/quizzes
Content-Type: application/json

{
  "title": "Basic Math Quiz",
  "startTime": "2025-06-09T01:01:01",
  "endTime": "2025-06-11T01:01:01",
  "questions": [
    { "text": "What is 2+2?", "answer": "4", "score": 10 },
    { "text": "What is 3*3?", "answer": "9", "score": 10 }
  ]
}
```

---

### 📄 Get All Quizzes

```http
GET /api/quizzes
```

---

### 🔍 Get Quiz By ID

```http
GET /api/quizzes/{quizId}
```

---

### 👤 Join Quiz

```http
POST /api/quizzes/{quizId}/join
Content-Type: application/json

{
  "userId": "user123"
}
```

---

### 📝 Submit Quiz Answers

```http
POST /api/quizzes/1/results
{
  "userId": "user456",
  "answers": {
    "1": "4",
    "2": "910"
  }
}
```

---

### 📊 Get Leaderboard

```http
GET /api/leaderboard
```
