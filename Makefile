start-kafka:
	docker run -p 2181:2181 -p 9092:9092 -d --name kafka --env ADVERTISED_HOST=${DH}.dev.awin.com --env ADVERTISED_PORT=9092 spotify/kafka

remove-kafka:
	docker stop kafka
	docker rm kafka

kafka-producer:
	docker exec -it kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-console-producer.sh --topic TestTopic --broker-list d-lhr1-docker-304.dev.awin.com:9092

set-topic-retention:
	docker exec -it kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-configs.sh --zookeeper localhost:2181 --alter --entity-type topics --entity-name TestTopic --add-config retention.ms=120000

test-other:
	docker exec -it kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-configs.sh --zookeeper localhost:2181 --alter --entity-type topics --entity-name TestTopic --add-config retention.check.interval.minutes=2

.PHONY: start-kafka
