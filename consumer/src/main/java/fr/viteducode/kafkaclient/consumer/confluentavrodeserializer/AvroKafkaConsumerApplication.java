package fr.viteducode.kafkaclient.consumer.confluentavrodeserializer;

import fr.viteducode.avro.ChampionKey;
import fr.viteducode.avro.ChampionValue;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class AvroKafkaConsumerApplication {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "0.0.0.0:9092");
        properties.put("schema.registry.url", "http://0.0.0.0:8081");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer1");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<ChampionKey, ChampionValue> kafkaConsumer = new KafkaConsumer<>(properties);

        kafkaConsumer.subscribe(Collections.singleton("champion_avro"));

        try {

            while (true) {

                ConsumerRecords<ChampionKey, ChampionValue> records = kafkaConsumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<ChampionKey, ChampionValue> record : records) {
                    System.out.printf("Champion id : %s - Champion name : %s", record.key().getId(), record.value().getName());
                }
                kafkaConsumer.commitAsync();
            }
        } finally {
            kafkaConsumer.close();
        }
    }
}
