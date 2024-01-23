# java-springboot-cqrs-architecture-sample

CQRS architecture sample using Java and Springboot

## Configurar Debezium

```shell
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d '
{
 "name": "cqrs-test-connector",
 "config": {
   "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
   "database.hostname": "postgres",
   "database.port": "5432",
   "database.user": "postgres",
   "database.password": "admin123",
   "database.dbname" : "postgresdb",
   "database.server.name": "dbserver1",
   "plugin.name": "pgoutput"
 }
}'
```

## Conectarse al Kafka

kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic dbserver1.public.customer --from-beginning

[Docker to compose](https://www.composerize.com/)

