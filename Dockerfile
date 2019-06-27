FROM openjdk:11.0.3-jdk-slim-stretch
ADD ./target/jms-app.jar /app/
CMD ["java","-jar","app/jms-app.jar"]
EXPOSE 8081
