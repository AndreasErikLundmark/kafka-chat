# kafka-chat

Setup Kafka server locally:
https://kafka.apache.org/documentation/#quickstart_download
1. download kafka
2. go to kafka dir
3. Create id: KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
4. Set id: bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c config/kraft/server.properties
5. Start server port 9092: bin/kafka-server-start.sh config/kraft/server.properties

6. Create a "test-topic": bin/kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
7. Start producer: bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
8. Start consumer: bin/kafka-console-consumer.sh --topic test-topic --from-beginning --bootstrap-server localhost:9092

-----------------------------

Build a Producer app

https://docs.spring.io/spring-kafka/reference/introduction.html

1. Spring initialzr -> maven -> kafka and web dependencies
2. 

