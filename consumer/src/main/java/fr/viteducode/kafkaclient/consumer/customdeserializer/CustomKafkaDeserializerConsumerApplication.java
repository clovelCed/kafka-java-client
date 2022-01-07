package fr.viteducode.kafkaclient.consumer.customdeserializer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class CustomKafkaDeserializerConsumerApplication {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "0.0.0.0:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer1");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        KafkaConsumer<String, Champion> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singleton("champion_topic"));

        try {
            while (true) {
                ConsumerRecords<String, Champion> records = kafkaConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, Champion> record : records) {
                    System.out.printf("Message key : %s - Champion id : %s - Champion value : %s -  Message offset : %d - Message partition : %d\n",
                            record.key(), record.value().getId(), record.value().getName(), record.offset(), record.partition());
                }
                kafkaConsumer.commitAsync();
            }
        } finally {
            kafkaConsumer.close();
        }
    }
}
