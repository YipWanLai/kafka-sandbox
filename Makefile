start-kafka:
	docker run -p 2181:2181 -p 9092:9092 -d --name kafka --env ADVERTISED_HOST=${DH}.dev.awin.com --env ADVERTISED_PORT=9092 spotify/kafka

remove-kafka:
	docker stop kafka
	docker rm kafka

kafka-producer:
	docker exec -it kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-console-producer.sh --topic TestTopic --broker-list ${DH}.dev.awin.com:9092

.PHONY: start-kafka
