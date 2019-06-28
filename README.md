# JmsApp
App using a simple API with external ActiveMQ broker. Delivers messages to queue listeners and topic subscribers.

## To run:
  - `mvn clean install`
  - `docker-compose up`
  
Exposed endpoints:

`GET  8080/all`  returns all messages from an in-memory DB 

`POST 8080/send/{topic}/{message}`  sends {message} to {topic}

`POST 8080/send/queue/{message}`  sends {message} to queue

  **registered topics: A, BC** every other topic will return 404
