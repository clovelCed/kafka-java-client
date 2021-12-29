package fr.viteducode.kafkaclient.producer.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class SimpleKafkaProducerApplication {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "0.0.0.0:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "simple_topic";
        String key = "key";
        String value = "value";
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record, (recordMetadata, exception) -> {
            if (exception != null) {
                System.out.println(exception.getMessage());
            }
        });

        producer.close();
    }
}
