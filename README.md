# Rabbit MQ 

## Run in Docker:
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management

## Manual Initialisation:
1. Create exchange: TestExchange
2. Create topic: TestTopic
3. Create binding: TestExchange -> Queue: TestTopic, Routing Key: testRoute

## Auto initialisation:
Auto initialisation of Exchanges, Topics, bindings is made in consumer module.

## Test:
Use test-producer-consumer.http for testing consumer and produces.
